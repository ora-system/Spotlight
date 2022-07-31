# Spotlight

Spotlight – Coding Challenge

Business Requirement:

Business Requirement is to tag the top sellers on product listing pages and search result pages so that the customers are encouraged to buy popular products.

Rank the products sold on the web storefront for the previous 24 hours based on the quantity and consider the top 100 articles as 'Top Sellers'. 


Solution:

I have used SAP Commerce 2205 version to implement the solution. I have used accelerator template to create b2caccelerator.

The solution implemented to achieve the business requirement is by using Adaptive search functionality. A simple search profile “toSellerSimpleProfile” is created and the products which are intended to promote are assigned to the search profile based on the sales history of last 24 hours. 

A cron job “topSellerCronJob” is written to extract the top 100 products based on the quantity sold in the last 24 hours and linked to the search profile. 

If the selected category or search has any of its products as top sellers, then those products appear on the top of the list in the product listing page. 

Cronjob must trigger every 1 hour so that the past 24 hours results will be calculated accurately.

A custom cronjob type called TopSellerCronjob has been implemented it contains attributes of searchProfile and basesite. In this way we can create separate jobs for each site.

Execution:

Please use the code along with SAP Commerce Suite 2205 and sapmachine-17.

Custom Code paths:

Conjob: https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/job/TopSellerPerformableJob.java

Dao : https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/topseller/dao/TopSellerDao.java

Dao.implementation: https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/topseller/dao/impl/DefaultTopSellerDao.java

Service: https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/topseller/service/TopSellerService.java

Service.implementation: https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/topseller/service/impl/DefaultTopSellerService.java

Resolver for Sorting : https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/src/au/com/spotlight/core/search/resolver/TopSellerProductValueResolver.java

Impex: 

https://github.com/ora-system/Spotlight/blob/main/core-customization/hybris/bin/custom/spotlight/spotlightcore/resources/spotlightcore/import/common/spotlight.impex


![image](https://user-images.githubusercontent.com/35945754/182023776-a8047e1d-d503-4b49-8c8f-5ac82bc24278.png)
