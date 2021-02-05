DELETE FROM address a
    USING (select address_fk as addr_fk from
        (SELECT address_fk, count(address_fk)
        FROM person_address
        GROUP BY person_address.address_fk
        HAVING count(*) = 1) per_addr
        join (select address_fk as fk from person_address where person_fk = 14) one
        ON per_addr.address_fk = one.fk
        ) p_a
WHERE a.id = p_a.addr_fk;
