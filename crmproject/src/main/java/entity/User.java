package entity;

public class User {

	private int id;
	private String fullname;
	private String email;
	private String password;
	private String phone;
	private String firstName;
	private String lastName;
	// private int idRole;
	private Role role; // Doi lai thanh doi tuong luon
	
	/*
	 * Luu y: Neu nhu cot idRole la khoa ngoai cua bang kkhac thi se tao ra doi tuong
	 * the cho id khoa ngoai (O day thi la doi tuong Role(id, name, description))
	 */
	
	public User(String fullname, String email, String password, String phone, int roleId) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = new Role(); // Khởi tạo đối tượng role
        this.role.setId(roleId); // Gán giá trị cho id của đối tượng role
	}
	
	public User(int id, String fullname, String email, String password, String phone, int roleId) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = new Role(); // Khởi tạo đối tượng role
        this.role.setId(roleId); // Gán giá trị cho id của đối tượng role
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/*
	 * 	public int getIdRole() {
		return idRole;
		}
		public void setIdRole(int idRole) {
			this.idRole = idRole;
		}
	
	 */
	// Generate lai getter va setter moi cua role
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	
	
}
