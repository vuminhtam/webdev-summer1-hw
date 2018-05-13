SELECT * FROM user;
ALTER TABLE user
  ADD role varchar(255) default null;
  
  ALTER TABLE user
  ADD phone varchar(255) default null;
  
    
  ALTER TABLE user
  ADD email varchar(255) default null;
  
    
  ALTER TABLE user
  ADD date_of_birth datetime default null;