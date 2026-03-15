public class LinkedIn implements SocialMedia {
    private int id;
    private String text;
    private String image;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }else{
            System.out.println("Invalid ID");
        }
    }

    public void setText(String text) {
        if (text != null && !text.isEmpty()) {
            this.text = text;
        } else {
            System.out.println("Text cannot be empty");
        }
    }

    public void setImage(String image) {
        if (image != null && !image.isEmpty()) {
            this.image = image;
        } else {
            System.out.println("Image cannot be empty");
        }
    }

    // getters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    @Override
    public void setPostData(int id, String text, String image) {
        setId(id);
        setImage(image);
        setText(text);
    }

    @Override
    public void showPostData() {
        System.out.println("You are on application Linkedin");
        System.out.println("Post ID: " + getId());
        System.out.println("Post Text: " + getText());
        System.out.println("Post Image: " + getImage());

    }
}
