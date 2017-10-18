import com.hf.adminWeb.ChihuoApplication;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HF on 2017/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@SpringBootTest(classes = ChihuoApplication.class)
public class ActivitiTest {

    @Resource
    private ProcessEngine processEngine;
    /**
     * 最基本的act流程引擎
     */
    @Test
    public void basicAct() {

        // 创建流程引擎
        // ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();

        //部署流程定义文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/demo/HelloWorld1.bpmn").deploy();
        System.out.println("deployment:" + deployment.getId());

        // 验证已经部署的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        System.out.println(processDefinition.getKey());

        // 启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorld");
        // helloWorld:2:2504
        System.out.println("pid:"+processInstance.getId() + ".pdid="+processInstance.getProcessDefinitionId());
    }

    @Test
    public void basicActDeployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<String> list = repositoryService.getDeploymentResourceNames("helloWorld:2:2504");
        for (String name : list) {
            System.out.println(name);
        }
    }

    @Test
    public void basicActDel() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("2504");
    }
}
