select u.user_id,u.username,td.training_id,td.training_date, count(*) 
      from Users u,Training_details td 
           where u.user_id = td.user_id
           GROUP BY u.user_id,u.username,td.training_id,td.training_date
           HAVING count(*)>1
           ORDER BY td.training_date DESC;
