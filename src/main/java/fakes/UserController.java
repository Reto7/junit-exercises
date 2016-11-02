package fakes;

public class UserController {

    private Database db = FileDatabase.getInstance();
    private UserValidator userValidator = new UserValidator(); // per Default ein "echter" UserValidator

    public UserController(){}

    public UserController(UserValidator userValidator) {
        // ueberschreiben
        this.userValidator = userValidator;
    }

    public UserController(UserValidator userValidator, Database db) {
        this.userValidator = userValidator;
        this.db = db;
    }

    public Message create(User user){
        if(user == null){
            throw new IllegalArgumentException("user required");
        }
        // dies ist der problematische Aufruf bzgl. einer Abhaengigkeit
        // der userValidator ist das Problem, somit muessen wir ihn mocken und hier ermoeglichen dass
        // von aussen entweder ein gemockter userValidator mitgegeben wird oder per default der echte
        // userValidator verwendet wird.
        Boolean canCreate = userValidator.isValidUsername(user.getUsername())
                            && !userValidator.doesUsernameExist(user.getUsername());
        if(canCreate){
            db.addUser(user);
            return Message.createOK();
        }else{
            return Message.createNotOK();
        }
    }
}
