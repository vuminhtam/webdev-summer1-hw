
-- SELECT l.id as id, l.title as title, l.module_id as module_id FROM lesson l, course c, module m WHERE c.id = 22 AND m.id = 52 AND l.module_id = 52;
-- 
-- CREATE PROCEDURE findLessonsByModuleviaCourse(cid int, mid int)
-- BEGIN 
-- SELECT l.id as id, l.title as title, l.module_id as module_id 
-- FROM lesson l, course c, module m 
-- WHERE c.id = cid AND m.id = mid AND l.module_id = mid
-- END$$
--  DELIMITER ;
--  
--  CALL findLessonsByModuleviaCourse(22, 52);
 
 SELECT * FROM user;
 SELECT  * FROM course;
  SELECT * FROM lesson;
 SELECT * FROM topic;
 SELECT * FROM widget;
 
 
 