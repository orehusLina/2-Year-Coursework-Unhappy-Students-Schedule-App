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
    database: 'test'
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
    const username = req.body.username;
    const password = req.body.password;

    connection.query('INSERT INTO table1 (username, password) VALUES (?, ?)', [username, password], (err, result) => {
        if (!err && result.affectedRows != 0) {
            res.status(200).send('inserted successfully');
        } else {
            console.error(err);
            res.status(500).send('Ошибка сервера');
        }
    });
});

app.post('/login', (req, res) => {
    const sql = 'SELECT * FROM table1 WHERE username = ?';
    connection.query(sql, [req.body.username], (err, result) => {
        if (!err && result.length > 0 && result[0].password === req.body.password) {
            res.status(200).send();
        } else {
            res.status(401).send('wrong credentials');
            console.error(err);
        }
    });
});

app.delete('/delete', (req, res) => {
    const sql = 'DELETE FROM table1 WHERE username = ?';
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

module.exports = app; // Export the configured app instance
