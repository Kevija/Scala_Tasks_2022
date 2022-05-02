SELECT * FROM customers;
WHERE Country != "USA";

SELECT * FROM customers;
WHERE Country = "Germany";

SELECT * FROM employees;
WHERE Title LIKE "Sales % agent";

SELECT DISTINCT BillingCountry FROM invoices;