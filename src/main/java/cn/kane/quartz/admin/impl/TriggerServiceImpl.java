package cn.kane.quartz.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kane.quartz.admin.domain.AdminTrigger;
import cn.kane.quartz.admin.domain.AdminTriggerExample;
import cn.kane.quartz.admin.mapper.AdminTriggerMapper;
import cn.kane.quartz.admin.service.TriggerService;

@Service
public class TriggerServiceImpl implements TriggerService {
	
	@Autowired
	private AdminTriggerMapper adminTriggerMapper ;
	
	public List<AdminTrigger> getALLTriggers(Long jobId){
		AdminTriggerExample condition = new AdminTriggerExample();
		condition.createCriteria().andJobidEqualTo(jobId) ;
		return adminTriggerMapper.selectByExample(condition );
	}

	public void addTrigger(AdminTrigger trigger){
		adminTriggerMapper.insertSelective(trigger) ;
	}

	public void updateTrigger(AdminTrigger trigger) {
		adminTriggerMapper.updateByPrimaryKey(trigger) ;
	}

	public void deleteTrigger(Long triggerId) {
		adminTriggerMapper.deleteByPrimaryKey(triggerId) ;
	}

	@Override
	public AdminTrigger getTrigger(Long triggerId) {
		return adminTriggerMapper.selectByPrimaryKey(triggerId);
	}

}
