SELECT
    pg.familyName AS family_name,
    pg.givenName AS given_name,
    pg.middleName AS middle_name,
    pg.birthDate AS birth_date,
    pd.contactRelationship AS relationship
FROM
    HPPersonGeneric pg
JOIN
    HPPersonDependant pd
ON
    pg.sysId = pd.HPRelatedPersonSysId
WHERE
    pd.HPPersonGenericSysId = (SELECT sysId FROM HPPersonGeneric WHERE personId = 'test');
