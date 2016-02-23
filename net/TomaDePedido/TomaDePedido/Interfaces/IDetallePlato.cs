namespace TomaDePedido.Interfaces
{
    using TomaDePedido.Enums;

    public interface IDetallePlato
    {
        int Cantidad { get; set; }
        Enums.EstadoPedido Estado { get; }
        string Comentario { get; set; }
        int CodigoPlato { get; set; }
        int CodigoDetallePlato { get; set; }

        void AsignarComentario(string comentario);

        string ObtenerComentario();

        int ObtenerCodigoDetalle();

        int ObtenerCodigoPlato();
    }
}