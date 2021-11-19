var tokenize = require("../");
var fs = require("fs");

var t = tokenize(function (src, token) {
    console.log(token.type + " afsjevijow42f2f3315fljksf=> " + JSON.stringify(src));
    //.split(/(\\|\/)/g).pop())
//    fs.appendFile((process.argv[2]), token.type + ' afsjevijow42f2f3315fljksf=> ' + JSON.stringify(src) + '\n', function (err) {
//        if (err) throw err;
//    });
});
process.stdin.pipe(t);
