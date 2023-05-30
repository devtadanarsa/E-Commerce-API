# <h1 align="center">API E-Commerce</h1>
<p align="center">by Devta Danarsa</p>

# **Pengenalan**
Selamat datang di program API E-Commerce<br>
Dengan program ini, kalian dapat menerima atau mengubah data 


# **Alur Program**
### Request Method GET
Request Method GET digunakan untuk mendapatkan data dari database yang disediakan. Beberapan Request Method GET yang dapat digunakan pada API ini dapat dilihat sebagai berikut.
<br>

####  1. Mendapatkan semua user 
Untuk mendapatkan semua user, dapat menggunakan url seperti berikut.
``` http://localhost:8060/users``` <br>
Hasil eksekusi url diatas dapat dilihat sebagai berikut.
``` [
    {
        "firstName": "Muhammad",
        "lastName": "Devta",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "08123132123",
        "id": 1,
        "type": "Seller",
        "email": "@gmail.com"
    },
    {
        "firstName": "Anak Agung",
        "lastName": "Liangga",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "0812123123",
        "id": 2,
        "type": "Buyer",
        "email": "@gmail.com"
    },
    {
        "firstName": "Made",
        "lastName": "Prayatna Dharma",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "021314143",
        "id": 3,
        "type": "Seller",
        "email": "@gmail.com"
    },
    {
        "firstName": "Bagus",
        "lastName": "Wahyu",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "012013013",
        "id": 4,
        "type": "Buyer",
        "email": "@gmail.com"
    }
] 
```
<br>

#### 2) Mendapatkan user berdasarkan id</p>
Untuk mendapatkan user berdarkan id, dapat menggunakan url sebagai berikut.

``` http://localhost:8060/users/id ```

Hasil eksekusi dapat dilihat sebagai berikut.

``` [
    {
        "firstName": "Muhammad",
        "lastName": "Devta",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "08123132123",
        "id": 1,
        "type": "Seller",
        "email": "@gmail.com"
    }
]
```

#### 3) Mendapatkan user berdasarkan type</p>
Untuk mendapatkan user berdasarkan type, dapat menggunakan url sebagai berikut.

```http://localhost:8060/users?type=buyer```

Hasil eksekusi dapat dilihat sebagai berikut : 

```[
    {
        "firstName": "Anak Agung",
        "lastName": "Liangga",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "0812123123",
        "id": 2,
        "type": "Buyer",
        "email": "@gmail.com"
    },
    {
        "firstName": "Bagus",
        "lastName": "Wahyu",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "012013013",
        "id": 4,
        "type": "Buyer",
        "email": "@gmail.com"
    }
]
 ```

#### 4) Mendapatkan user menggunakan condition
Untuk mendapatkan user menggunakan condition, dapat menggunakan url berikut :
``` http://localhost:8060/users?field=id&cond=smallerEqual&val=2```

Hasil eksekusi dari url diatas, dapat dilihat sebagai berikut.
```
[
    {
        "firstName": "Muhammad",
        "lastName": "Devta",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "08123132123",
        "id": 1,
        "type": "Seller",
        "email": "@gmail.com"
    },
    {
        "firstName": "Anak Agung",
        "lastName": "Liangga",
        "addresses": [
            {
                "province": "\"Bali\"",
                "city": "\"Denpasar\"",
                "postcode": "\"81021\"",
                "line2": "\"Jawa\"",
                "line1": "\"Jalan Madura\""
            },
            {
                "province": "Bali",
                "city": "Kupang",
                "postcode": "81231",
                "line2": "Agung",
                "line1": "Jalan Gunung"
            }
        ],
        "phoneNumber": "0812123123",
        "id": 2,
        "type": "Buyer",
        "email": "@gmail.com"
    }
]
```

#### 5) Mendapatkan produk yang dimiliki user
Untuk mendapatkan produk yang dimiliki salah satu user dapat menggunakan url berikut
``` http://localhost:8060/users/1/products ```

Hasil eksekusi dapat dilihat sebagai berikut
```agsl
[
    {
        "seller": 1,
        "price": "20000",
        "description": "Susu Kambing",
        "id": 1,
        "title": "Susu",
        "stock": 5
    },
    {
        "seller": 1,
        "price": "80000",
        "description": "Baju Crop Top",
        "id": 2,
        "title": "Baju",
        "stock": 7
    }
]
```

#### 6) Mendapatkan order yang dimiliki user
Untuk mendapatkan order yang dimiliki user dapat menggunakan url sebagai berikut
``` http://localhost:8060/users/2/orders ```

Hasil eksekusi dapat dilihat sebagai berikut.
```agsl
[
    {
        "note": 0,
        "total": 100000,
        "discount": 0,
        "id": 1,
        "is_paid": "True",
        "buyer": 2
    },
    {
        "note": 0,
        "total": 60000,
        "discount": 0,
        "id": 2,
        "is_paid": "False",
        "buyer": 2
    }
]
```
#### 6) Melihat review yang dilakukan user

Untuk melihat review yang dilakukan oleh user dapat menggunakan url berikut :
```agsl http://localhost:8060/users/2/reviews ```

Hasil eksekusi dapat dilihat sebagai berikut :
```agsl
[
    {
        "star": 5,
        "description": "Kontol!!!!",
        "order": 1
    }
]
```

#### 7) Melihat semua produk
Untuk melihat semua produk yang ada dapat menggunakan url berikut :
``` http://localhost:8060/products ```

Hasil eksekusi adalah sebagai berikut
```agsl
[
    {
        "seller": 1,
        "price": "20000",
        "description": "Susu Kambing",
        "id": 1,
        "title": "Susu",
        "stock": 5
    },
    {
        "seller": 1,
        "price": "80000",
        "description": "Baju Crop Top",
        "id": 2,
        "title": "Baju",
        "stock": 7
    },
    {
        "seller": 3,
        "price": "120000",
        "description": "kopi tai",
        "id": 3,
        "title": "kopi",
        "stock": 7
    }
]
```

#### 8) Melihat produk berdasarkan id
Untuk melihat produk berdasarkan id dapat menggunakan url berikut
``` http://localhost:8060/products/id ```

Hasil eksekusi dapat dilihat sebagai berikut
```agsl
[
    {
        "seller": 1,
        "price": "20000",
        "description": "Susu Kambing",
        "id": 1,
        "title": "Susu",
        "stock": 5
    }
]
```

#### 9) Melihat produk menggunakan kondisi
Untuk melihat produk menggunakan kondisi dapat menggunakan url berikut
``` http://localhost:8060/products/1 ```

Hasil eksekusi dapat dilihat sebagai berikut
```agsl
[
    {
        "seller": 1,
        "price": "20000",
        "description": "Susu Kambing",
        "id": 1,
        "title": "Susu",
        "stock": 5
    }
]
```

<br><h3> Request Method POST </h3>
Request Method POST digunakan untuk menambah record baru ke dalam sebuah tabel. Untuk menggunakan Request Method POST pada API ini, bisa mengirimkan url seperti pada contoh berikut.

``` http://localhost:8060/users ```

File JSON :

```agsl
{
    "first_name" : "Alex",
    "last_name" : "Hunter",
    "email" : "brobro@gmail.com",
    "phone_numer" : "0872121212",
    "type" : "Buyer"
}
```

Pada contoh diatas, '/users' dan file JSON dapat diganti sesuai dengan kebutuhan Anda. Jika dieksekusi, maka akan menghasilkan output seperti berikut :

``` 1 rows inserted! ```


<br><h3> Request Method PUT </h3>
Request Method PUT digunakan untuk mengubah data yang ada pada tabel. Untuk menggunakan Request Method PUT pada API ini, bisa mengirimkan url seperti pada contoh berikut.

``` http://localhost:8060/users/5 ```

File JSON :
```
{
    "first_name" : "Alex",
    "last_name" : "Dede",
    "email" : "brobro@gmail.com",
    "phone_numer" : "0872121212",
    "type" : "Buyer"
}
```

Pada contoh diatas '/users', '/5', dan file JSON dapat diganti sesuai dengan kebutuhan Anda. Jika dieksekusi, maka akan menghasilkan output sebagai berikut :

``` 1 rows updated! ```


<br><h3> Request Method DELETE </h3>
Request Method DELETE digunakan untuk menghapus record yang ada pada sebuah tabel. Untuk menggunakan Request Method DELETE pada API ini, bisa mengirimkan url seperti pada contoh berikut.

``` http://localhost:8060/users/5 ```

Pada contoh diatas, '/users' dan '/5' dapat diganti sesuai dengan kebutuhan Anda. Jika dieksekusi, maka akan menghasilkan output sebagai berikut :

``` 1 rows deleted! ```