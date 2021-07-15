# Jarvis-Backend # 
###### Spring Boot REST API for order/inventory management system ######
> Spring, Spring Boot, Hibernate, JPA, Querydsl
***
_This project is consist of two sub-projects which are **[Jarvis-frontend](https://github.com/kwonyongju/jarvis-frontend)** and **[Jarvis-backend](https://github.com/kwonyongju/jarvis-backend)**_
You are looking at **Jarvis-backend**


#### API Documentation
- This project is deployed on AWS and you can only access the data through the **[website](https://yongju-kwon.com/jarvis)**

| # | Method | URL   | Description |
| --- | ----- | ----- | -----   |
| Menu | GET | /api/v1/menu | Finds all menu registered in system |
|| POST | /api/v1/menu | Add a menu to system |
| Inventory| GET | /api/v1/inventory | Finds current inventory state(stock quantity) in system |
| Order | GET | /api/v1/orders | Finds all ingredients orders made by all managers |
|  | GET | /api/v1/order/{id} | Finds all ingredients orders made by a manager |
|  | POST | /api/v1/order | Adds(Places) an ingredients order |
| Purchase | Get | /api/v1/purchase | Finds all purchases made by customers |
|  | Get | /api/v1/purchase/{id} | Finds all purchases made by a customer |
|  | POST | /api/v1/purchase | Adds(Makes) a purchase of item(s) |