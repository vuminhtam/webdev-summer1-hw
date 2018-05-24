
SELECT * FROM course;
SELECT * FROM module;
SELECT * FROM lesson;

SELECT l.id as id, l.title as title, l.module_id as module_id FROM lesson l, course c, module m WHERE c.id = 22 AND m.id = 52 AND l.module_id = 52;

CREATE PROCEDURE findLessonsByModuleviaCourse(cid int, mid int)
BEGIN 
SELECT l.id as id, l.title as title, l.module_id as module_id 
FROM lesson l, course c, module m 
WHERE c.id = cid AND m.id = mid AND l.module_id = mid
END$$
 DELIMITER ;