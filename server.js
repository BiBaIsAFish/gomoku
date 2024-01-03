const express = require("express");
const app = express();
const port = 3333;

app.use("/start", (req, res)=> {
  res.status(200).send("start");
  console.error(`[JS][INFO] start`);
  console.log(`6`);
  console.log(`W`);
})

app.use("/java/:row/:col", (req, res) => {
  res.status(200).send("OK");
  console.log(`${req.params.row} ${req.params.col}`);
});

app.use("/", (req, res) => {
  res.status(200).send("Everything is OK!!!");
});

app.listen(port, () => {
  console.error(`[JS][INFO] Server running...`);
});
