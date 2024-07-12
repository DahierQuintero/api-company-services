SELECT
    sr.id AS `serviceRequest_id`,
    c.name AS `customer_application_name`,
    rs.id AS `request_status_number`,
    rs.status_name AS `request_status_name`,
    ca.id AS `castor_activity_id`,
    ca.name AS `castor_activity_name`
FROM
    service_request sr
    INNER JOIN customer c ON sr.id_customer = c.id
    INNER JOIN request_status rs ON sr.id_request_status = rs.id
    INNER JOIN castor_activity ca ON sr.id_castor_activity = ca.id;