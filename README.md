Documentation
StreamingProject
﻿

Videos
﻿

POST
Criar videos
http://localhost:8080/streaming
﻿

Body
raw (json)
json
{
    "title": "cod zombies",
    "description": "jogo muito bom",
    "url": "https://www.youtube.com/watch?v=u31ncTWqt-8"
}
PUT
like videos
http://localhost:8080/streaming/favoritos/65a60356a0ef5745ea3118ae?isLiked=true
﻿

Query Params
isLiked
true
DELETE
delete Video
http://localhost:8080/streaming/65a968089810355a4f9d5592
﻿

PUT
edit video
http://localhost:8080/streaming/65ad8c638f08227fb708858e
﻿

Body
raw (json)
json
{
    "title": "fluxo",
    "description": "jogo muito bom",
    "url": "https://www.youtube.com/watch?v=u31ncTWqt-8",
    "categoria": {
        "id":"65ab184983b1565a6adc2605",
        "name": "Comédia"
    }
}
GET
get paginated
http://localhost:8080/streaming/paginado?page=0&size=10&order=DESC
﻿

Query Params
page
0
size
10
order
DESC
title
fluxo
Category
﻿

POST
create Category
http://localhost:8080/category?name=Comedia
﻿

Query Params
name
Comedia
GET
find by id
http://localhost:8080/category/65ab184983b1565a6adc2605
﻿

GET
Get all category
http://localhost:8080/category

