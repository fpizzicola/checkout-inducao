# checkout-inducao  

## Preference  

### Get  
GET http://localhost:9988/preference/:pref-id  
Ex:
```
curl --location --request GET 'http://localhost:9988/preference/687809950-9de96869-9a0b-43e5-9012-88c87a41032f'
```

### Post  
POST http://localhost:9988/preference/  
`
{"itens" : [{"title", "description", "quantity", "unitPrice"}]}
`  

Ex:
```
curl --location --request POST 'http://localhost:9988/preference/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "itens" : [
        {
            "title" : "sample",
            "description" : "sample item",
            "quantity" : 1,
            "unitPrice" : 100 
        }
    ]
}'```