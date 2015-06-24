DROP PROCEDURE IF EXISTS GEN_CUSTOM_PRODUCT_REL;
--/
CREATE PROCEDURE GEN_CUSTOM_PRODUCT_REL(IN CUSTOM_GROUP_ID VARCHAR(32), IN LABLE_IDS VARCHAR(1024), IN LABLE_NAMES VARCHAR(1024))
    MODIFIES SQL DATA
BEGIN
   DECLARE SQL_FOR_SELECT VARCHAR(4000); 
   SET LABLE_IDS=CONCAT('(\'',REPLACE(LABLE_IDS,'::','\',\''),'\')');
   SET LABLE_NAMES=CONCAT('(\'',REPLACE(LABLE_NAMES,'::','\',\''),'\')');
   IF LABLE_IDS is not null
   THEN
    SET SQL_FOR_SELECT = CONCAT(' INSERT INTO ci_custom_group_product_rel(CUSTOM_GROUP_ID,PRODUCT_ID,ADD_TIME) 
SELECT ','\'',CUSTOM_GROUP_ID,'\'',',PRODUCT_ID,DATE_FORMAT(SYSDATE(), \'%Y-%m-%d %H:%i:%s\') FROM poc_product_info WHERE PRODUCT_ID IN (
SELECT product_id FROM poc_product_label_rel_data WHERE product_label_id IN (
SELECT product_label_id FROM poc_product_label_info WHERE data_status_id = \'2\' AND product_label_id IN ',LABLE_IDS ,')) AND status = 4;');
    SET @sql = SQL_FOR_SELECT;
    PREPARE stmt FROM @sql;         -- 预处理动态sql语句
       EXECUTE stmt ;                        -- 执行sql语句
       DEALLOCATE PREPARE stmt;      -- 释放prepare
   ELSEIF LABLE_NAMES is not null 
   THEN
   SET SQL_FOR_SELECT = CONCAT(' INSERT INTO ci_custom_group_product_rel(CUSTOM_GROUP_ID,PRODUCT_ID,ADD_TIME) 
SELECT ','\'',CUSTOM_GROUP_ID,'\'',',PRODUCT_ID,DATE_FORMAT(SYSDATE(), \'%Y-%m-%d %H:%i:%s\') FROM poc_product_info WHERE PRODUCT_ID IN (
SELECT product_id FROM poc_product_label_rel_data WHERE product_label_id IN (
SELECT product_label_id FROM poc_product_label_info WHERE data_status_id = \'2\' AND label_name IN ',LABLE_NAMES ,')) AND status = 4;');
 SET @sql = SQL_FOR_SELECT;
    PREPARE stmt FROM @sql;         -- 预处理动态sql语句
       EXECUTE stmt ;                        -- 执行sql语句
       DEALLOCATE PREPARE stmt;      -- 释放prepare
   END IF;
 
END
/