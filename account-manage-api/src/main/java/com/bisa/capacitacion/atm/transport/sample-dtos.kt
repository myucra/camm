package com.bisa.capacitacion.atm.transport

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonRootName
import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Miguel A. Vega
 * @version 1.0: `sample-dtos`.kt;
 */
@JsonRootName("Producto")
data class ProductoDto(
    @Schema(
        example = "ABCD123XYZ",
        required = true, description = "Solamente acepta caracteres alfanumericos",
        title = "Identificador del producto", pattern = "\\b[a-zA-Z0-9]+\\b"
//        , format = "fmt", allowableValues = ["1", "2", "3"], defaultValue = "1"
    )
    var id: String?,
    @Schema(required = true) var descripcion: String?,
    var disponible: Boolean? = true
)

@JsonRootName("CuerpoPedido")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class CuerpoPedidoDto(
    @Schema(title = "Items del pedido") open var items: List<ItemPedidoDto>? = listOf(),
    @Schema(title = "Comentarios del pedido", maxLength = 200) open var comentarios: String?
)

@JsonRootName("Pedido")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class PedidoDto(
    @Schema(description = "Identificador del producto") var id: String?,
    @Schema(description = "Fecha de creacion en milisegundos") var creadoEn: Long?,
    @Schema(description = "Fecha de finalizacion en milisegundos") var ultimoCambio: Long?,
    @Schema(description = "Estado dle pedido") var estado: String?,
    items: List<ItemPedidoDto>? = listOf(),
    comentarios: String?
) : CuerpoPedidoDto(items, comentarios)

@JsonRootName("ItemPedido")
data class ItemPedidoDto(
    @Schema(description = "Identificador del producto a agregar")
    var productoId: Int?,
    @Schema(description = "Cantidad de productos a agregar", exclusiveMaximumValue = 1)
    var cantidad: Int?
)

data class AcknowledgementDto(
    @Schema(
        title = "Identificador generado",
        description = "Generalmente se utiliza este identificador para acceder a los detalles objeto"
    )
    var referenceId: String?,
    @Schema(description = "La hora y fecha exactas en las que se ha respondido", title = "epoch time millis")
    var timestamp: Long?
) {
    constructor(referenceId: String?) : this(referenceId, System.currentTimeMillis())
}