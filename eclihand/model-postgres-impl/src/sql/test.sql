 select * from per_person WHERE per_person::text ~ '1986';
 select pla.* from PLA_PLAYER pla LEFT JOIN PER_PERSON per ON per.id = pla.pla_per_id WHERE upper(pla::text) ~ upper('pedrero') OR upper(per::text) ~ upper('pedrero') ;
( select * from PLA_PLAYER pla LEFT JOIN PER_PERSON per ON per.id = pla.pla_per_id);