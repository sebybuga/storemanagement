# storemanagement
An API that acts as a store management tool. The store components has been simplified. The application is a demo showing API development skills.

Installation steps:
1. Clone repository https://github.com/sebybuga/storemanagement.git
2. Install MySQL 8.3.0
3. Create DB user defined in src\main\resources\application.properties
4. Run DB script:
src\main\resources\db\createStoreManagementDatabase.sql
5. Import src\main\resources\postman\Store_Management.postman_collection.json

/api/order/delete and /api/product/delete endpoints are allowed only for SUPER_ADMIN roles. Add this roles in application.properties->spring.security.user.roles to send delete requests.  


