const express = require('express')

const app = express()
const port = 3000
const db = require('mysql2')
app.use(express.json())
let connection = db.createConnection({
        host     : 'localhost',
            user     : 'root',
            password : '111111',
            database : 'test'
        })

app.post('/login',(req,res)=>{

    let sql  ='SELECT * FROM table1 WHERE username = '+'"'+ req.body.username+ '"'
   connection.query(sql,(err,result)=>{
    if(!err && result != null){
        res.status(200).send()
    } else{
        res.send('wrong credentials').end
        console.error(err)
    }
   })
})

app.post('/signup',(req,res)=>{
    
    var username = req.body.username
    var  password = req.body.password
 
connection.query('insert into table1 Values (?,?)',[username,password],(err,result)=>{
   if(!err && result.affectedRows!=0){
       res.status(200).send()
   }else{
       console.error(err);
   }
})
})
app.delete('/delete',(req,res)=>{
    let sql  ='DELETE FROM table1 WHERE username = '+'"'+ req.body.username+ '"'    
    connection.query(sql,(err,result)=>{
        if(!err && result != null){
            res.status(200).send()
            console.log('user deleted from users')
        } else{
            res.send('wrong credentials').end
            console.error(err)
        }
    })
})

app.listen(port,()=>{
    console.log('$erver is listening....')
})
