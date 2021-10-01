// el array de los videos
//var url='http://10.1.26.162';
/***work***/
var url='http://10.1.26.162:8090';
/***home***/
//var url='http://10.1.43.236:8090';


cargar()
// la longitud del array de los videos

function cargar(){
    
    //fetch(url+'/Video/file.php')
    fetch(url+'/r')
    .then(response => response.json())
    .then(json => repro(json)
        )
        
    //console.log(jsono)
    //return jsono;
}

function repro(json){
    let vids = []
    json.forEach(element => {
        vids.push(url+element.url+element.name); 
    })

    console.log(vids)

    let leng = vids.length;
    console.log(vids.length)
    // un numero utilizado para saber que video se está reproduciendo
    let n = 0;
    // utilizo el evento "ended" para saber si el video se ha acabado
    vid.addEventListener("ended",()=>{
        // si el video se ha acabado cambia el atributo src
        vid.setAttribute("src", vids[n%leng]);
        n++;
        })
}
