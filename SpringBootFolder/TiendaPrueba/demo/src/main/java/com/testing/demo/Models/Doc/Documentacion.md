
# DOCUMENTATION

<ins>Date 07-08-2022</ins><br>
<p>I've got some problems with my GenerationType.AUTO which the starting point of my random data was not correctly settled, the way that i've solved it was just chaing to IDENTITY.</p>
For more information i found out this web page where it's quite complete.
https://ngdeveloper.com/generationtype-identity-vs-generationtype-sequence-vs-generationtype-auto/
<br><p>I had another problem which my id_employee was not adding up well it was another counting problem that i had to fix it, i ended up removing the default's value constructor of my employee class and that solved the case.</p><br>
<p>Before</p>

```java
    public Empleado() {
        // super();
        // ++cont;
        // this.id = cont;
    }
        
```
<br>
<p>After</p>
<br>

```java
    public Empleado() {

    }
```


