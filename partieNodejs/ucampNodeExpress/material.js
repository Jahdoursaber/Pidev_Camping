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


router.post("/create", (req, res) => {
    var id = parseInt(req.body.id);
    var idCat=parseInt(req.body.idCat);
    var prix= parseFloat(req.body.prix);


    pool.query("INSERT INTO `material` VALUES (?,?,?,?,?,?,?,?)",
        [id,
        req.body.nom,
            req.body.description,
            req.body.refrence,
            req.body.photo,
            req.body.etat,
            prix,
            idCat],
        (err, material, fields) => {
           if(err)
            console.log(err)
            res.status(200);
            res.json(material)

        });
})

router.post("/edit/:id", (req, res) => {
    var idCat=parseInt(req.body.idCat);
    var prix= parseFloat(req.body.prix);

    pool.query("update `material` set nom=? , description=?, refrence=?,photo=?,etat=?,prix=?,idCat=? where id=?",
    [
        req.body.nom,
            req.body.description,
            req.body.refrence,
            req.body.photo,
            req.body.etat,
            prix,
            idCat,
            req.params.id],
        (err, material, fields) => {

            res.status(200);
            res.json(material)

        });
})

router.post("/delete/:id", (req, res) => {
    console.log(req.body.label);
    pool.query("delete from `material` where id=?",
        [

            req.params.id],
        (err, category, fields) => {

            res.status(200);
            res.json(category)

        });
})

router.get("/findAll", (req, res) => {
    pool.query("SELECT * FROM `material` ",
        (err, rows, fields) => {
            res.status(200)
            res.send(rows)
        })
})
router.get("/findById/:id", (req, res) => {
    pool.query("SELECT * FROM `material` where id=? ",
    [req.params.id],
        (err, rows, fields) => {
            res.status(200)
            res.send(rows)
        })
})
router.get("/findByCategorie/:id", (req, res) => {
    pool.query("SELECT * FROM `material` where idCat=? ",
    [req.params.id],
        (err, rows, fields) => {
            res.status(200)
            res.send(rows)
        })
})


module.exports = router;