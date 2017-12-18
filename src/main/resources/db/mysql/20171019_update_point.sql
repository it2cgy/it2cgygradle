--mt_project，mc_inverter，mc_electric_meter，mc_grid_cabinet，mc_meteor_monitor，mc_track_controll_unit，mc_iv_device_channel，mc_measurement_box
ALTER TABLE `pvmts_point_info`
ADD COLUMN `equipmentcontainer_name_en`  varchar(128) NULL AFTER `description_type_en`

UPDATE pvmts_point_info t,mt_project m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=0;

UPDATE pvmts_point_info t,mc_inverter m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=3;

UPDATE pvmts_point_info t,mc_electric_meter m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=5;

UPDATE pvmts_point_info t,mc_grid_cabinet m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=6;

UPDATE pvmts_point_info t,mc_meteor_monitor m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=8;

UPDATE pvmts_point_info t,mc_track_controll_unit m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=9;
UPDATE pvmts_point_info t,mc_iv_device_channel m set t.equipmentcontainer_name_en = m.englishname WHERE t.equipmentcontainer_id=m.ID AND t.equipmentcontainer_tableid=10;