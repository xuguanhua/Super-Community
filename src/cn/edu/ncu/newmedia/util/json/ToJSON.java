package cn.edu.ncu.newmedia.util.json;

import java.util.List;

import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.bean.Department;

public class ToJSON {
	/**
	 * @author SwordPlayer
	 * @param 由社团信息组成的ArrayList
	 * @return json字符串
	 */
	public static String communityAccountListToJSON(List<CommunityAccount> list) {
		
		StringBuilder string = new StringBuilder();
		if(list != null && list.size() > 0) {
			string.append("{\"communityAccounts\":[");
			for(int i = 0; i < list.size(); i++) {
				string.append("{");
				
				string.append("\"id\":");
				string.append(list.get(i).getId());
				string.append(",");
				
				string.append("\"communityPhone\":\"");
				string.append(list.get(i).getCommunityPhone() + "\"");
				string.append(",");
				
				string.append("\"communityName\":\"");
				string.append(list.get(i).getCommunityName() + "\"");
				string.append(",");
				
				string.append("\"loginTime\":\"");
				string.append(list.get(i).getLoginTime().toString() + "\"");
				string.append(",");
				
				string.append("\"passwordError\":");
				string.append(list.get(i).getPasswordError());
				string.append(",");
				
				string.append("\"state\":");
				string.append(list.get(i).getState());
				
				string.append("}");
				if(list.size() != 1 && i != list.size() - 1) {
					string.append(",");
				}
			}
			string.append("]}");
		}
		return string.toString();
	}

	/**
	 * @author SwordPlayer
	 * @param 由部门信息组成的ArrayList
	 * @return json字符串
	 */
	public static String departmentsListToJSON(List<Department> list) {
		StringBuilder string = new StringBuilder();
		if(list != null && list.size() > 0) {
			string.append("{\"departments\":[");
			for(int i = 0; i < list.size(); i++) {
				string.append("{");
				
				string.append("\"id\":");
				string.append(list.get(i).getId());
				string.append(",");
				
				string.append("\"departmentId\":");
				string.append(list.get(i).getDepartmentId());
				string.append(",");
				
				string.append("\"communityId\":");
				string.append(list.get(i).getCommunityId());
				string.append(",");
				
				string.append("\"departmentDes\":\"");
				string.append(list.get(i).getDepartmentDes() + "\"");
				string.append(",");
				
				string.append("\"departmentName\":\"");
				string.append(list.get(i).getDepartmentName() + "\"");
				string.append(",");
				
				string.append("\"createAt\":");
				string.append(list.get(i).getCreateAt().getTime());
				
				string.append("}");
				if(list.size() != 1 && i != list.size() - 1) {
					string.append(",");
				}
			}
			string.append("]}");
		}
		return string.toString();
	}
}