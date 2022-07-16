const express = require('express');
const mysql = require('mysql');

const router = express.Router();



const pool = mysql.createPool({
    connectionLimit: 10,
    host: "localhost",
    user: "root",
    database: "camping"

})


function getConnection() {
    return pool;
}


router.post("/create/:label", (req, res) => {

    pool.query("INSERT INTO `categorie`(`label`) VALUES (?)",
        [
            req.params.label],
        (err, category, fields) => {

            res.status(200);
            res.json(category)

        });
})

router.post("/edit/:id", (req, res) => {
    console.log(req.body.label);
    pool.query("update `categorie` set label=? where id=?",
        [
            req.body.label,
            req.params.id],
        (err, category, fields) => {

            res.status(200);
            res.json(category)

        });
})

router.post("/delete/:id", (req, res) => {
    console.log(req.body.label);
    pool.query("delete from `categorie` where id=?",
        [

            req.params.id],
        (err, category, fields) => {

            res.status(200);
            res.json(category)

        });
})

router.get("/findAll", (req, res) => {
    pool.query("SELECT * FROM `categorie` ",
        (err, rows, fields) => {
            res.status(200)
            res.send(rows)
        })
})

router.get("/findById/:id", (req, res) => {
    pool.query("SELECT * FROM `categorie` where id=? ",
    [req.params.id],
        (err, rows, fields) => {
            res.status(200)
            res.send(rows)
        })
})



module.exports = router;