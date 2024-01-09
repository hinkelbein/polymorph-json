namespace csharp_polymorph_json
{
    public class AnotherMessage : BaseMessage
    {
        public int i { get; set; }

        public AnotherMessage(int i) : base(
            typeof(AnotherMessage))
        {
            this.i = i;
        }

        public AnotherMessage()
        {
        }
    }
}
