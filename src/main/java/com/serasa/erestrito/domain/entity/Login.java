package com.serasa.erestrito.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "login")
public class Login implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_name", unique = true) 
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialNonExpired;

	@Column(name = "enabled")
	private Boolean enabled;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	public Login() {
		
	}


	public Login(Long id, String userName, String password, Boolean accountNonExpired,
			Boolean accountNonLocked, Boolean credentialNonExpired, Boolean enabled, List<Permissao> permissoes) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialNonExpired = credentialNonExpired;
		this.enabled = enabled;
		this.permissoes = permissoes;
	}

	
	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
		
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "login_permissao", 
	joinColumns = { @JoinColumn(name = "id_login") }
	, inverseJoinColumns = {	@JoinColumn(name = "id_permissao") })
	private List<Permissao> permissoes;

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permissao permissao : this.permissoes) {
			roles.add(permissao.getDescription());
		}
		return roles;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialNonExpired, enabled, id, password,
				permissoes, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialNonExpired, other.credentialNonExpired)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(permissoes, other.permissoes) && Objects.equals(userName, other.userName)
				&& Objects.equals(enabled, other.enabled);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialNonExpired() {
		return credentialNonExpired;
	}

	public void setCredentialNonExpired(Boolean credentialNonExpired) {
		this.credentialNonExpired = credentialNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Permissao> getPermissions() {
		return permissoes;
	}


	public void setPermissions(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}


	
	
	
	
	

}
