# Las Cuentas Claras Front
  
## Introducción
Esta aplicación web permite gestionar los gastos de un grupo de amigos.


## Descripción funcional
Las funciones principales son:
* Como usuario, quiero acceder al grupo de gastos compartidos de mi grupo de amigos. Para cada gasto quiero visualizar la siguiente información ordenada por el último pago realizado: 
** Persona que realizó el pago 
** Importe del pago 
** Descripción del pago. 
** Fecha del pago 
* Como usuario, quiero añadir una persona a mi grupo de amigos. 
* Como usuario, quiero añadir un pago. 
* Como usuario, quiero un balance para saber cuánto dinero tiene que pagar o recibir cada persona del grupo para que todos hubiéramos pagado lo mismo y no tener deudas entre el grupo de amigos. 


## Construcción
Este proyecto se construye mediante la herramienta NPM. Para preparar el proyecto ejecute en la raíz:
````shell script
npm install
````

### Ejecución en local
Primero se debe de iniciar el proyecto "Las Cuentas Claras Back". Para ellos diríjase al apartado ***Ejecución en contenedor Docker*** del fichero [Back](../las-cuentas-claras-back) y siga los pasos

Puede iniciar la aplicación web mediante el comando:
````shell script
ng server -o
````
Esto abrirá la aplicación en su navegador, en la ruta `http://localhost:4200/`

### Ejecución en contenedor Docker
El proyecto dispone de un fichero DockerFile para poder ejecutar la aplicación dentro de un contendor

Para generar la imagen, ejecute en la raíz del proyecto:
````shell script
docker build -t las-cuentas-claras-front .
````

Una vez generada la imagen, ejecute el contendor con:
````shell script
docker run --rm -d -it -p 8300:8080 --name las-cuentas-claras-front las-cuentas-claras-front
````
La aplicación se abrirá en su navegador en la ruta `http://localhost:8300/`