# Las Cuentas Claras
  
## Introducción
Esta aplicación gestionará los gastos de un grupo de amigos.


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


## API REST
Este servicio resume los puntos de entrada al servicio vía API Rest:

| Operación          | Método                | Path             | Descripción   
| ------------------ | --------------------  | ---------------- | ----------------------------|
| Obtener Pagos  |  GET  |  /payments  | Retorna una lista de pagos ordenada por el último pago |
| Añadir pago  |  POST  |  /payments  | Añadir un pago de un amigo. |
| Obtener balance  |  GET  |  /payments/balances  | Retorna un balance de la situción de cada amigo para igualar cuentas. |
| Añadir amigo  |  POST  |  /friends  | Añadir una persona a mi grupo de amigos. |
| Listar amigos  |  GET  |  /friends  | Listar los amigos de mi grupo. |



## Construcción
Este proyecto se construye mediante la herramienta Maven. Si se quieren ejecutar todas las fases, basta con ejecutar 
desde la ráiz del proyecto

````shell script
mvn clean install
````

### Ejecución en local

Primero se debe de iniciar una bbdd Mongodb, se puede usar el docker-compose incluido en el proyecto:
````shell script
docker-compose -f docker-compose.yml up
````

El proyecto se ejecuta mediante la instrucción:
````shell script
mvn spring-boot:run
````
El api del servicio se puede consultar localmente en: http://localhost:8080/swagger-ui.html

### Ejecución en contenedor Docker
El proyecto dispone de un fichero DockerFile para poder ejecutar la aplicación dentro de un contendor

Para generar la imagen ejecute en la raíz del proyecto:
````shell script
mvn clean package
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
docker build -t las-cuentas-claras .
````

Una vez generada la imagen, ejecute el contendor con:
````shell script
docker run --rm -p 8080:8080 las-cuentas-claras
````
