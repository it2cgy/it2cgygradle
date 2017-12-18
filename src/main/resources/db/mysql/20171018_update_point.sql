ALTER TABLE `pvmts_point_info`
ADD COLUMN `description_type_en`  varchar(64) NULL AFTER `point_english_name`;

UPDATE pvmts_point_info t,equipmenttype t2 set t.description_type_en = t2.`NAME` WHERE t.description_type=t2.DESCRIPTION