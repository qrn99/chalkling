export const DEBUG = false

export const getServerURL = () => {
    if (DEBUG){
        return "http://localhost:1337/chalkling.herokuapp.com"
    } else {
        return "https://chalkling.herokuapp.com"
    }
}