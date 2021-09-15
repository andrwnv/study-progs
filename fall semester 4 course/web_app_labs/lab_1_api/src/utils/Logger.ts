export default class Logger {
    static Info(msg: string) {
        console.log(`[INFO]: ${msg}`);
    }

    static Error(msg: string) {
        console.log(`[ERR]: ${msg}`);
    }

    static Warning(msg: string) {
        console.log(`[WARN]: ${msg}`);
    }
}
