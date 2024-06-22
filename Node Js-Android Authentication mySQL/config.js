const express = require('express');
const db = require('mysql2');
const csv = require('csv-parser');
const fs = require('fs');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

let connection = db.createConnection({
    host: 'localhost',
    user: 'root',
    password: '229335',
    database: 'test2'
});

connection.connect((err) => {
    if (err) {
        console.error(err);
    } else {
        console.log('connected to database.');
    }
});

let resits = [];

// Функция для загрузки данных из CSV-файла
function loadCSVData() {
    fs.createReadStream(path.join(__dirname, 'resits.csv'))
        .pipe(csv())
        .on('data', (row) => {
            resits.push(row);
        })
        .on('end', () => {
            console.log('CSV файл успешно загружен');
        });
}

loadCSVData();

app.post('/signup', (req, res) => {
    const { username, password, firstName, lastName, role } = req.body;

    connection.query('INSERT INTO users (username, password, name, surname, role) VALUES (?, ?, ?, ?, ?)', 
    [username, password, firstName, lastName, role], 
    (err, result) => {
        if (!err && result.affectedRows != 0) {
            res.status(200).send('inserted successfully');
        } else {
            console.error(err);
            res.status(500).send('Ошибка сервера');
        }
    });
});

app.post('/login', (req, res) => {
    const sql = 'SELECT * FROM users WHERE username = ?';
    connection.query(sql, [req.body.username], (err, result) => {
        if (!err && result.length > 0 && result[0].password === req.body.password) {
            const user = {
                id: result[0].id,
                username: result[0].username,
                password: result[0].password,
                firstName: result[0].name,
                lastName: result[0].surname,
                role: result[0].role
            };
            res.status(200).json(user);
        } else {
            res.status(401).send('wrong credentials');
            console.error(err);
        }
    });
});

// Новый маршрут для получения данных о пользователе
app.get('/api/user', (req, res) => {
    const username = req.query.username;
    const sql = 'SELECT * FROM users WHERE username = ?';
    connection.query(sql, [username], (err, result) => {
        if (!err && result.length > 0) {
            const user = result[0];
            res.status(200).json({
                username: user.username,
                firstName: user.name,
                lastName: user.surname,
                role: user.role
            });
        } else {
            res.status(404).send('User not found');
            console.error(err);
        }
    });
});

app.delete('/delete', (req, res) => {
    const sql = 'DELETE FROM users WHERE username = ?';
    connection.query(sql, [req.body.username], (err, result) => {
        if (!err && result.affectedRows != 0) {
            res.status(200).send('Пользователь успешно удален');
            console.log('user deleted from table1');
        } else {
            res.status(404).send('Пользователь не найден');
            console.error(err);
        }
    });
});

// Новый маршрут для получения данных о пересдачах
app.get('/resits', (req, res) => {
    res.json(resits);
});

module.exports = app;
