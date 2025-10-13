package com.jbbcch.taskmanager;

import com.jbbcch.smarttaskmanager.SmartTaskManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class TaskmanagerApplicationTests {

	@Test
	void verifyApplicationModuleStructure() {
		 var modules = ApplicationModules.of(SmartTaskManagerApplication.class).verify();
		 modules.forEach(System.out::println);
	}

}
