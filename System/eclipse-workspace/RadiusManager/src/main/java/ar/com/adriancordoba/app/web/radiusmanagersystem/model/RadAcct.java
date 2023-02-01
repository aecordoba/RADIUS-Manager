/*
 * 		RadAcct.java
 *   Copyright (C) 2023  Adrián E. Córdoba [software.asia@gmail.com]
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 		RadAcct.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 31, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "radacct")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class RadAcct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "radacctid")
	private Long id;
	@Column(name = "acctsessionid")
	private String acctSessionId;
	@Column(name = "acctuniqueid")
	private String acctUniqueId;
	@Column(name = "username")
	private String userName;
	@Column(name = "realm")
	private String realm;
	@Column(name = "nasipaddress")
	private String nasIpAddress;
	@Column(name = "nasportid")
	private String nasPortId;
	@Column(name = "nasporttype")
	private String nasPortType;
	@Column(name = "acctstarttime")
	private LocalDateTime acctStartTime;
	@Column(name = "acctupdatetime")
	private LocalDateTime acctUpdateTime;
	@Column(name = "acctstoptime")
	private LocalDateTime acctStopTime;
	@Column(name = "acctinterval")
	private Integer acctInterval;
	@Column(name = "acctsessiontime")
	private Integer acctSessionTime;
	@Column(name = "acctauthentic")
	private String acctAuthentic;
	@Column(name = "connectinfo_start")
	private Integer connectInfoStart;
	@Column(name = "connectinfo_stop")
	private Integer connectInfoStop;
	@Column(name = "acctinputoctets")
	private Long acctInputOctets;
	@Column(name = "acctoutputoctets")
	private Long acctOutputOctets;
	@Column(name = "calledstationid")
	private String calledStationId;
	@Column(name = "callingstationid")
	private String callingStationId;
	@Column(name = "acctterminatecause")
	private String acctTerminateCause;
	@Column(name = "servicetype")
	private String serviceType;
	@Column(name = "framedprotocol")
	private String framedProtocol;
	@Column(name = "framedipaddress")
	private String framedIpAddress;
	@Column(name = "framedipv6address")
	private String framedIpv6Address;
	@Column(name = "framedipv6prefix")
	private String framedIpv6Prefix;
	@Column(name = "framedinterfaceid")
	private String framedInterfaceId;
	@Column(name = "delegatedipv6prefix")
	private String delegatedIpv6prefix;
	@Column(name = "class")
	private String clazz;

	/**
	 * 
	 */
	public RadAcct() {
		super();
	}

	/**
	 * @param id
	 * @param acctSessionId
	 * @param acctUniqueId
	 * @param userName
	 * @param realm
	 * @param nasIpAddress
	 * @param nasPortId
	 * @param nasPortType
	 * @param acctStartTime
	 * @param acctUpdateTime
	 * @param acctStopTime
	 * @param acctInterval
	 * @param acctSessionTime
	 * @param acctAuthentic
	 * @param connectInfoStart
	 * @param connectInfoStop
	 * @param acctInputOctets
	 * @param acctOutputOctets
	 * @param calledStationId
	 * @param callingStationId
	 * @param acctTerminateCause
	 * @param serviceType
	 * @param framedProtocol
	 * @param framedIpAddress
	 * @param framedIpv6Address
	 * @param framedIpv6Prefix
	 * @param framedInterfaceId
	 * @param delegatedIpv6prefix
	 * @param clazz
	 */
	public RadAcct(Long id, String acctSessionId, String acctUniqueId, String userName, String realm,
			String nasIpAddress, String nasPortId, String nasPortType, LocalDateTime acctStartTime,
			LocalDateTime acctUpdateTime, LocalDateTime acctStopTime, Integer acctInterval, Integer acctSessionTime,
			String acctAuthentic, Integer connectInfoStart, Integer connectInfoStop, Long acctInputOctets,
			Long acctOutputOctets, String calledStationId, String callingStationId, String acctTerminateCause,
			String serviceType, String framedProtocol, String framedIpAddress, String framedIpv6Address,
			String framedIpv6Prefix, String framedInterfaceId, String delegatedIpv6prefix, String clazz) {
		super();
		this.id = id;
		this.acctSessionId = acctSessionId;
		this.acctUniqueId = acctUniqueId;
		this.userName = userName;
		this.realm = realm;
		this.nasIpAddress = nasIpAddress;
		this.nasPortId = nasPortId;
		this.nasPortType = nasPortType;
		this.acctStartTime = acctStartTime;
		this.acctUpdateTime = acctUpdateTime;
		this.acctStopTime = acctStopTime;
		this.acctInterval = acctInterval;
		this.acctSessionTime = acctSessionTime;
		this.acctAuthentic = acctAuthentic;
		this.connectInfoStart = connectInfoStart;
		this.connectInfoStop = connectInfoStop;
		this.acctInputOctets = acctInputOctets;
		this.acctOutputOctets = acctOutputOctets;
		this.calledStationId = calledStationId;
		this.callingStationId = callingStationId;
		this.acctTerminateCause = acctTerminateCause;
		this.serviceType = serviceType;
		this.framedProtocol = framedProtocol;
		this.framedIpAddress = framedIpAddress;
		this.framedIpv6Address = framedIpv6Address;
		this.framedIpv6Prefix = framedIpv6Prefix;
		this.framedInterfaceId = framedInterfaceId;
		this.delegatedIpv6prefix = delegatedIpv6prefix;
		this.clazz = clazz;
	}

	/**
	 * @param acctSessionId
	 * @param acctUniqueId
	 * @param userName
	 * @param realm
	 * @param nasIpAddress
	 * @param nasPortId
	 * @param nasPortType
	 * @param acctStartTime
	 * @param acctUpdateTime
	 * @param acctStopTime
	 * @param acctInterval
	 * @param acctSessionTime
	 * @param acctAuthentic
	 * @param connectInfoStart
	 * @param connectInfoStop
	 * @param acctInputOctets
	 * @param acctOutputOctets
	 * @param calledStationId
	 * @param callingStationId
	 * @param acctTerminateCause
	 * @param serviceType
	 * @param framedProtocol
	 * @param framedIpAddress
	 * @param framedIpv6Address
	 * @param framedIpv6Prefix
	 * @param framedInterfaceId
	 * @param delegatedIpv6prefix
	 * @param clazz
	 */
	public RadAcct(String acctSessionId, String acctUniqueId, String userName, String realm, String nasIpAddress,
			String nasPortId, String nasPortType, LocalDateTime acctStartTime, LocalDateTime acctUpdateTime,
			LocalDateTime acctStopTime, Integer acctInterval, Integer acctSessionTime, String acctAuthentic,
			Integer connectInfoStart, Integer connectInfoStop, Long acctInputOctets, Long acctOutputOctets,
			String calledStationId, String callingStationId, String acctTerminateCause, String serviceType,
			String framedProtocol, String framedIpAddress, String framedIpv6Address, String framedIpv6Prefix,
			String framedInterfaceId, String delegatedIpv6prefix, String clazz) {
		super();
		this.acctSessionId = acctSessionId;
		this.acctUniqueId = acctUniqueId;
		this.userName = userName;
		this.realm = realm;
		this.nasIpAddress = nasIpAddress;
		this.nasPortId = nasPortId;
		this.nasPortType = nasPortType;
		this.acctStartTime = acctStartTime;
		this.acctUpdateTime = acctUpdateTime;
		this.acctStopTime = acctStopTime;
		this.acctInterval = acctInterval;
		this.acctSessionTime = acctSessionTime;
		this.acctAuthentic = acctAuthentic;
		this.connectInfoStart = connectInfoStart;
		this.connectInfoStop = connectInfoStop;
		this.acctInputOctets = acctInputOctets;
		this.acctOutputOctets = acctOutputOctets;
		this.calledStationId = calledStationId;
		this.callingStationId = callingStationId;
		this.acctTerminateCause = acctTerminateCause;
		this.serviceType = serviceType;
		this.framedProtocol = framedProtocol;
		this.framedIpAddress = framedIpAddress;
		this.framedIpv6Address = framedIpv6Address;
		this.framedIpv6Prefix = framedIpv6Prefix;
		this.framedInterfaceId = framedInterfaceId;
		this.delegatedIpv6prefix = delegatedIpv6prefix;
		this.clazz = clazz;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the acctSessionId
	 */
	public String getAcctSessionId() {
		return acctSessionId;
	}

	/**
	 * @param acctSessionId the acctSessionId to set
	 */
	public void setAcctSessionId(String acctSessionId) {
		this.acctSessionId = acctSessionId;
	}

	/**
	 * @return the acctUniqueId
	 */
	public String getAcctUniqueId() {
		return acctUniqueId;
	}

	/**
	 * @param acctUniqueId the acctUniqueId to set
	 */
	public void setAcctUniqueId(String acctUniqueId) {
		this.acctUniqueId = acctUniqueId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the realm
	 */
	public String getRealm() {
		return realm;
	}

	/**
	 * @param realm the realm to set
	 */
	public void setRealm(String realm) {
		this.realm = realm;
	}

	/**
	 * @return the nasIpAddress
	 */
	public String getNasIpAddress() {
		return nasIpAddress;
	}

	/**
	 * @param nasIpAddress the nasIpAddress to set
	 */
	public void setNasIpAddress(String nasIpAddress) {
		this.nasIpAddress = nasIpAddress;
	}

	/**
	 * @return the nasPortId
	 */
	public String getNasPortId() {
		return nasPortId;
	}

	/**
	 * @param nasPortId the nasPortId to set
	 */
	public void setNasPortId(String nasPortId) {
		this.nasPortId = nasPortId;
	}

	/**
	 * @return the nasPortType
	 */
	public String getNasPortType() {
		return nasPortType;
	}

	/**
	 * @param nasPortType the nasPortType to set
	 */
	public void setNasPortType(String nasPortType) {
		this.nasPortType = nasPortType;
	}

	/**
	 * @return the acctStartTime
	 */
	public LocalDateTime getAcctStartTime() {
		return acctStartTime;
	}

	/**
	 * @param acctStartTime the acctStartTime to set
	 */
	public void setAcctStartTime(LocalDateTime acctStartTime) {
		this.acctStartTime = acctStartTime;
	}

	/**
	 * @return the acctUpdateTime
	 */
	public LocalDateTime getAcctUpdateTime() {
		return acctUpdateTime;
	}

	/**
	 * @param acctUpdateTime the acctUpdateTime to set
	 */
	public void setAcctUpdateTime(LocalDateTime acctUpdateTime) {
		this.acctUpdateTime = acctUpdateTime;
	}

	/**
	 * @return the acctStopTime
	 */
	public LocalDateTime getAcctStopTime() {
		return acctStopTime;
	}

	/**
	 * @param acctStopTime the acctStopTime to set
	 */
	public void setAcctStopTime(LocalDateTime acctStopTime) {
		this.acctStopTime = acctStopTime;
	}

	/**
	 * @return the acctInterval
	 */
	public Integer getAcctInterval() {
		return acctInterval;
	}

	/**
	 * @param acctInterval the acctInterval to set
	 */
	public void setAcctInterval(Integer acctInterval) {
		this.acctInterval = acctInterval;
	}

	/**
	 * @return the acctSessionTime
	 */
	public Integer getAcctSessionTime() {
		return acctSessionTime;
	}

	/**
	 * @param acctSessionTime the acctSessionTime to set
	 */
	public void setAcctSessionTime(Integer acctSessionTime) {
		this.acctSessionTime = acctSessionTime;
	}

	/**
	 * @return the acctAuthentic
	 */
	public String getAcctAuthentic() {
		return acctAuthentic;
	}

	/**
	 * @param acctAuthentic the acctAuthentic to set
	 */
	public void setAcctAuthentic(String acctAuthentic) {
		this.acctAuthentic = acctAuthentic;
	}

	/**
	 * @return the connectInfoStart
	 */
	public Integer getConnectInfoStart() {
		return connectInfoStart;
	}

	/**
	 * @param connectInfoStart the connectInfoStart to set
	 */
	public void setConnectInfoStart(Integer connectInfoStart) {
		this.connectInfoStart = connectInfoStart;
	}

	/**
	 * @return the connectInfoStop
	 */
	public Integer getConnectInfoStop() {
		return connectInfoStop;
	}

	/**
	 * @param connectInfoStop the connectInfoStop to set
	 */
	public void setConnectInfoStop(Integer connectInfoStop) {
		this.connectInfoStop = connectInfoStop;
	}

	/**
	 * @return the acctInputOctets
	 */
	public Long getAcctInputOctets() {
		return acctInputOctets;
	}

	/**
	 * @param acctInputOctets the acctInputOctets to set
	 */
	public void setAcctInputOctets(Long acctInputOctets) {
		this.acctInputOctets = acctInputOctets;
	}

	/**
	 * @return the acctOutputOctets
	 */
	public Long getAcctOutputOctets() {
		return acctOutputOctets;
	}

	/**
	 * @param acctOutputOctets the acctOutputOctets to set
	 */
	public void setAcctOutputOctets(Long acctOutputOctets) {
		this.acctOutputOctets = acctOutputOctets;
	}

	/**
	 * @return the calledStationId
	 */
	public String getCalledStationId() {
		return calledStationId;
	}

	/**
	 * @param calledStationId the calledStationId to set
	 */
	public void setCalledStationId(String calledStationId) {
		this.calledStationId = calledStationId;
	}

	/**
	 * @return the callingStationId
	 */
	public String getCallingStationId() {
		return callingStationId;
	}

	/**
	 * @param callingStationId the callingStationId to set
	 */
	public void setCallingStationId(String callingStationId) {
		this.callingStationId = callingStationId;
	}

	/**
	 * @return the acctTerminateCause
	 */
	public String getAcctTerminateCause() {
		return acctTerminateCause;
	}

	/**
	 * @param acctTerminateCause the acctTerminateCause to set
	 */
	public void setAcctTerminateCause(String acctTerminateCause) {
		this.acctTerminateCause = acctTerminateCause;
	}

	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the framedProtocol
	 */
	public String getFramedProtocol() {
		return framedProtocol;
	}

	/**
	 * @param framedProtocol the framedProtocol to set
	 */
	public void setFramedProtocol(String framedProtocol) {
		this.framedProtocol = framedProtocol;
	}

	/**
	 * @return the framedIpAddress
	 */
	public String getFramedIpAddress() {
		return framedIpAddress;
	}

	/**
	 * @param framedIpAddress the framedIpAddress to set
	 */
	public void setFramedIpAddress(String framedIpAddress) {
		this.framedIpAddress = framedIpAddress;
	}

	/**
	 * @return the framedIpv6Address
	 */
	public String getFramedIpv6Address() {
		return framedIpv6Address;
	}

	/**
	 * @param framedIpv6Address the framedIpv6Address to set
	 */
	public void setFramedIpv6Address(String framedIpv6Address) {
		this.framedIpv6Address = framedIpv6Address;
	}

	/**
	 * @return the framedIpv6Prefix
	 */
	public String getFramedIpv6Prefix() {
		return framedIpv6Prefix;
	}

	/**
	 * @param framedIpv6Prefix the framedIpv6Prefix to set
	 */
	public void setFramedIpv6Prefix(String framedIpv6Prefix) {
		this.framedIpv6Prefix = framedIpv6Prefix;
	}

	/**
	 * @return the framedInterfaceId
	 */
	public String getFramedInterfaceId() {
		return framedInterfaceId;
	}

	/**
	 * @param framedInterfaceId the framedInterfaceId to set
	 */
	public void setFramedInterfaceId(String framedInterfaceId) {
		this.framedInterfaceId = framedInterfaceId;
	}

	/**
	 * @return the delegatedIpv6prefix
	 */
	public String getDelegatedIpv6prefix() {
		return delegatedIpv6prefix;
	}

	/**
	 * @param delegatedIpv6prefix the delegatedIpv6prefix to set
	 */
	public void setDelegatedIpv6prefix(String delegatedIpv6prefix) {
		this.delegatedIpv6prefix = delegatedIpv6prefix;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
}
