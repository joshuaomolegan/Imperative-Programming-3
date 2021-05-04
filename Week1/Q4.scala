class Triangle
class OpaqueTriangle extends Triangle

class Renderer {
    def accept(a: Triangle) = println("Accepted for rendering.")
}

class RayTracingRenderer extends Renderer {
    def accept(a: OpaqueTriangle) = println("Accepted for ray-trace rendering.")
}

val a: OpaqueTriangle = new OpaqueTriangle
val r1: Renderer = new RayTracingRenderer

/* Prints "Accepted for rendering" because assigning a RayTracingRenderer object to a reference of type Renderer restricts it to the methods available in 
   the Renderer class (the accept method that takes Triangles as inputs)*/
r1.accept(a)

val r2: RayTracingRenderer = new RayTracingRenderer
/* Prints "Accepted for ray-trace rendering". This is because the accept function has been overloaded, so the method used is the one with the parameters that
   match the input type, in this case the RayTracingRenderer accept method.*/
r2.accept(a)

// The following RayTracingRenderer class also changes the output for r1.accept
class NewRayTracingRenderer extends Renderer {
    override def accept(a: Triangle) = println("Accepted for ray-trace rendering.")
}

val r3: Renderer = new NewRayTracingRenderer
r3.accept(a)

