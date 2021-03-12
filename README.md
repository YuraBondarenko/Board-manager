# Board-manager 

## Application with a board, a column and a task

## Functionality(url = "localhost:8080"):
### Board("/boards"):
* Create - create board; return created object;
* Read-full - read by board id with relations("/information/'id'");
* Read - read by board id without relations("/'id'");
* Read-all;
* Update - update board without relations;
* Delete - delete board by id with relations;
* Upload Background image for board("/image/'id'?file-path='file-path''");
* Read Background image("/image/download/'id'").

### Column("columns"):
* Create - create column without relations;
* Update - update column by id;
* Delete - column by id with task list relations("/'id'").
### Task:
* Create - create task for specific column id;
* Update - update task by id;
* Delete - delete task by id("/'id'").

# To run the project locally:
1. Create schema in the PosgreSQL "board-manager"
2. Update file "src/main/resources/application.properties": 
    spring.datasource.username=root  // type your role in PosgreSQL
    spring.datasource.password=1234  // type your password
   


## To run the swagger url = "http://localhost:8080/swagger-ui/index.html"
   
