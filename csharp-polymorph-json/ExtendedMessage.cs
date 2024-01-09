namespace csharp_polymorph_json
{
    public class ExtendedMessage : BaseMessage
    {
        public string? additionalData { get; set; }

        public ExtendedMessage(string additionalData)
            : base(typeof(ExtendedMessage))
        {
            this.additionalData = additionalData;
        }

        public ExtendedMessage()
        {
        }
    }
}
