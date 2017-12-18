/* Set the defaults for DataTables initialisation */
$.extend(true, $.fn.dataTable.defaults, {
    "dom": "<'row'<'col-md-6 col-sm-6'l><'col-md-6 col-sm-6'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-5'i><'col-md-7 col-sm-7'p>>", // default layout with horizobtal scrollable datatable
    //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // datatable layout without  horizobtal scroll(used when bootstrap dropdowns used in the datatable cells)
    "language": {
        "lengthMenu": " _MENU_ records ",
        "paginate": {
            "previous": 'Prev',
            "next": 'Next',
            "page": "Page",
            "pageOf": "of"
        }
    },
    "pagingType": "bootstrap_full_number"
});

/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper",
    "sFilterInput": "form-control input-sm input-small input-inline",
    "sLengthSelect": "form-control input-sm input-xsmall input-inline"
});

// In 1.10 we use the pagination renderers to draw the Bootstrap paging,
// rather than  custom plug-in
$.fn.dataTable.defaults.renderer = 'bootstrap';
$.fn.dataTable.ext.renderer.pageButton.bootstrap = function (settings, host, idx, buttons, page, pages) {
    var api = new $.fn.dataTable.Api(settings);
    var classes = settings.oClasses;
    var lang = settings.oLanguage.oPaginate;
    var btnDisplay, btnClass;

    var attach = function (container, buttons) {
        var i, ien, node, button;
        var clickHandler = function (e) {
            e.preventDefault();
            if (e.data.action !== 'ellipsis') {
                api.page(e.data.action).draw(false);
            }
        };

        for (i = 0, ien = buttons.length; i < ien; i++) {
            button = buttons[i];

            if ($.isArray(button)) {
                attach(container, button);
            } else {
                btnDisplay = '';
                btnClass = '';

                switch (button) {
                case 'ellipsis':
                    btnDisplay = '&hellip;';
                    btnClass = 'disabled';
                    break;

                case 'first':
                    btnDisplay = lang.sFirst;
                    btnClass = button + (page > 0 ?
                        '' : ' disabled');
                    break;

                case 'previous':
                    btnDisplay = lang.sPrevious;
                    btnClass = button + (page > 0 ?
                        '' : ' disabled');
                    break;

                case 'next':
                    btnDisplay = lang.sNext;
                    btnClass = button + (page < pages - 1 ?
                        '' : ' disabled');
                    break;

                case 'last':
                    btnDisplay = lang.sLast;
                    btnClass = button + (page < pages - 1 ?
                        '' : ' disabled');
                    break;

                default:
                    btnDisplay = button + 1;
                    btnClass = page === button ?
                        'active' : '';
                    break;
                }

                if (btnDisplay) {
                    node = $('<li>', {
                        'class': classes.sPageButton + ' ' + btnClass,
                        'aria-controls': settings.sTableId,
                        'tabindex': settings.iTabIndex,
                        'id': idx === 0 && typeof button === 'string' ?
                            settings.sTableId + '_' + button : null
                    })
                        .append($('<a>', {
                                'href': '#'
                            })
                            .html(btnDisplay)
                    )
                        .appendTo(container);

                    settings.oApi._fnBindAction(
                        node, {
                            action: button
                        }, clickHandler
                    );
                }
            }
        }
    };

    attach(
        $(host).empty().html('<ul class="pagination"/>').children('ul'),
        buttons
    );
}

/***
Custom Pagination
***/

/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
    return {
        "iStart": oSettings._iDisplayStart,
        "iEnd": oSettings.fnDisplayEnd(),
        "iLength": oSettings._iDisplayLength,
        "iTotal": oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage": oSettings._iDisplayLength === -1 ?
            0 : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
        "iTotalPages": oSettings._iDisplayLength === -1 ?
            0 : Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
    };
};

/* Bootstrap style full number pagination control */
$.extend($.fn.dataTableExt.oPagination, {
    "bootstrap_full_number": {
        "fnInit": function (oSettings, nPaging, fnDraw) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function (e) {
                e.preventDefault();
                if (oSettings.oApi._fnPageChange(oSettings, e.data.action)) {
                    fnDraw(oSettings);
                }
            };

            $(nPaging).append(
                '<ul class="pagination">' +
                '<li class="prev disabled firstBtn" style="visibility: hidden;"><a href="#" title="' + oLang.sFirst + '">' + oLang.sFirst + '</a></li>' + //添加首页显示
                '<li class="prev disabled" style="visibility: hidden;"><a href="#" title="' + oLang.sPrevious + '">...</a></li>' +
                '<li class="next disabled"><a href="#" title="' + oLang.sNext + '">...</a></li>' +
                '<li class="next disabled"><a href="#" title="' + oLang.sLast + '">' + oLang.sLast + '</a></li>' + //添加尾页显示
                '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind('click.DT', {//绑定首页事件
                action: "first"
            }, fnClickHandler);
            $(els[1]).bind('click.DT', {
                action: "previous"
            }, fnClickHandler);
            $(els[2]).bind('click.DT', {
                action: "next"
            }, fnClickHandler);
            $(els[3]).bind('click.DT', {//绑定尾页事件
                action: "last"
            }, fnClickHandler);
        },

        "fnUpdate": function (oSettings, fnDraw) {
            var iListLength = oSettings.oLanguage.iListLength==undefined?10:oSettings.oLanguage.iListLength;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);
            
			
			if(oPaging.iTotalPages > iListLength){
				if(oPaging.iPage >= iHalf ){
					$('li.prev', an[i]).css('visibility','visible');
					$('li.next', an[i]).css('visibility','visible');	
				}else if(oPaging.iPage < iHalf){
					$('li.next', an[i]).css('visibility','visible');
				}
			}else if(oPaging.iTotalPages === iListLength){
				$('li.prev', an[i]).css('visibility','hidden');
				$('li.next', an[i]).css('visibility','hidden');
			}
			
            if (oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
                $('li.prev', an[i]).css('visibility','hidden');
                $('li.next', an[i]).css('visibility','hidden');
            } else if (oPaging.iPage <= iHalf) {
            	//当前点击小于5
                iStart = 1;
                iEnd = iListLength;
                $('li.prev', an[i]).css('visibility','hidden');
            } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
            	//当前点击大于等于最后5页
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
                $('li.next', an[i]).css('visibility','hidden');
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
                $('li.prev', an[i]).css('visibility','visible');
                $('li.next', an[i]).css('visibility','visible');
            }



            for (i = 0, iLen = an.length; i < iLen; i++) {
                if (oPaging.iTotalPages <= 0) {
                    $('.pagination', an[i]).css('visibility', 'hidden');
                } else {
                    $('.pagination', an[i]).css('visibility', 'visible');
                }

                // Remove the middle elements
                $('li:gt(1)', an[i]).filter(':not(.next)').remove();
                //$('li：gt(0)', an[i]).filter(':not(:last)').remove();

                // Add the new list items and their event handlers
                for (j = iStart; j <= iEnd; j++) {
                    sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                    $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
                        .insertBefore($('li.next:first', an[i])[0])
                        /*.insertBefore($('li:last', an[i])[0])*/
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                            fnDraw(oSettings);
                        });
                }

                // Add / remove disabled classes from the static elements
                if (oPaging.iPage === 0) {
                    $('li.prev', an[i]).addClass('disabled');
                } else {
                    $('li.prev', an[i]).removeClass('disabled');
                }
                
                if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
                    $('li.next', an[i]).addClass('disabled');
                } else {
                   $('li.next', an[i]).removeClass('disabled');
                }
            }
        }
    }
});

