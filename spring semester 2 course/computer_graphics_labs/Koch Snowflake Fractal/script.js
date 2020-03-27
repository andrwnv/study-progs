var canvas = document.getElementById("fractal_place");
var ctx    = canvas.getContext('2d');
var timer  = 0;

const defaultTimeOut = 500;

const resize = () => {
    canvas.width  = window.innerWidth;
    canvas.height = window.innerHeight;
    
    clearTimeout(timer);

    timer = setTimeout(() => {
        draw();
    }, defaultTimeOut);
};

window.onresize = resize;
resize();

const fractal = (options) => {
    var angle = options.angle;
    if (options.level <= 0) {
        ctx.beginPath();
        ctx.moveTo(options.coord[0].x, options.coord[0].y);
        ctx.lineTo(options.coord[1].x, options.coord[1].y);
        ctx.stroke();
        return;
    }

    var partOne = {
        x: options.coord[0].x + (options.coord[1].x - options.coord[0].x) * (options.child),
        y: options.coord[0].y + (options.coord[1].y - options.coord[0].y) * (options.child)
    };

    var partTwo = {
        x: options.coord[1].x - (options.coord[1].x - options.coord[0].x) * (options.child),
        y: options.coord[1].y - (options.coord[1].y - options.coord[0].y) * (options.child)
    };
    
    var partFour = {
        x: partTwo.x - partOne.x,
        y: partTwo.y - partOne.y
    };

    var partThree = {
        x: partOne.x + partFour.x * Math.cos(-angle) - partFour.y * Math.sin(-angle),
        y: partOne.y + partFour.y * Math.cos(-angle) + partFour.x * Math.sin(-angle)
    };
    var level = options.level - 1;
    
    fractal({
        coord: [options.coord[0], partOne],
        level: level,
        child: options.child,
        angle: options.angle,
    });
    
    fractal({
        coord: [partOne, partThree],
        level: level,
        child: options.child,
        angle: options.angle,
    });
    
    fractal({
        coord: [partThree, partTwo],
        level: level,
        child: options.child,
        angle: options.angle,
    });

    fractal({
        coord: [partTwo, options.coord[1]],
        level: level,
        child: options.child,
        angle: options.angle,
    });
};

const globalProperties = {
    level: 0,
    child: 1 / 3,
    angle: Math.PI / 3,
    lineWidth: 1,
    timer: true,
    "max level": 5,
    "line cap": "round"
};

const draw = () => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.lineWidth = globalProperties.lineWidth;
    ctx.lineCap = globalProperties["line cap"];
    ctx.shadowBlur = 5;
    ctx.shadowColor = "rgba(0,0,0,0.2)";
    ctx.strokeStyle = "#FF0000";

    let mw = canvas.width, mh = canvas.height;
    let w = mw - 2 * mw / 3;
    let tilt = Math.sin(Math.PI / 3);
    let offset = w * globalProperties.child * Math.sin(globalProperties.angle) / 2;
    let hs = mh / 2 - tilt * w / 2 + offset;

    let partOne = {
        x: canvas.width / 3,
        y: hs
    };

    let partTwo = {
        x: canvas.width - canvas.width / 3,
        y: hs
    };

    let partThree = {
        x: canvas.width / 2,
        y: canvas.height / 2 + tilt * w / 2 + offset
    };

    fractal({
        coord: [partOne, partTwo],
        level: globalProperties.level,
        child: globalProperties.child,
        angle: globalProperties.angle,
    });
    fractal({
        coord: [partThree, partOne],
        level: globalProperties.level,
        child: globalProperties.child,
        angle: globalProperties.angle,
    });
    fractal({
        coord: [partTwo, partThree],
        level: globalProperties.level,
        child: globalProperties.child,
        angle: globalProperties.angle,
    });
    ctx.closePath();
};
draw();

const gui = () => {
    let g = new dat.GUI();
    g.add(globalProperties, "level", 0, 5).step(1).listen();
    g.add(globalProperties, "max level", 0, 5).step(1).listen();

}; gui();

const drawLoop = () => {
    if (!globalProperties.timer)
        draw();

    requestAnimationFrame(drawLoop);
}; drawLoop();

setInterval(() => {
    if (globalProperties.timer) {
        globalProperties.level = ++globalProperties.level % (globalProperties["max level"] + 1);
        draw();
    }
}, defaultTimeOut);
