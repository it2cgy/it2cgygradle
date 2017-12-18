ALTER TABLE `pvmts_point_info`
ADD COLUMN `point_english_name`  varchar(64) NULL AFTER `point_value`

UPDATE pvmts_point_info t, measurementtype t1 set t.point_english_name=t1.englishname WHERE t.measurementtype_id = t1.ID