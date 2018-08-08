select e2.name , e1.name from employee e1 right join employee e2 on e1.id = e2.mgr_id;
select e1.id, e1.name from employee e1 join employee e2 on e1.id = e2.mgr_id group by e1.id having count(e1.id) > 1;
