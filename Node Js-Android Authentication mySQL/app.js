const express = require('express');
const config = require('./config');

const app = express();
const port = 3000;

app.use(express.json());
app.use('/api', config); // Use config as middleware for '/api' routes

app.listen(port, () => {
    console.log(`Сервер запущен на http://localhost:${port}`);
});
