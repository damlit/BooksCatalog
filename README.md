# BooksCatalog
It has catalog with books in JSON file. 
Springboot uses three endpoints: 
-> /api/book/{isdn} - to find book by ISDN or ID
-> /api/category/{categoryName}/books - to find books by category
-> /api/rating - to calculate and print authors sorted by average rating their books
