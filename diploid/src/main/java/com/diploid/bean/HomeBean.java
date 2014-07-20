/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("homeBean")
@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(HomeBean.class);

	private static final long serialVersionUID = 1L;

	private TabView tabView = new TabView();
	private List<Tab> dynamicTabList;
	private String userName;

	private boolean dataUploadDownloadTab;
	private boolean designWrorkflowTab;
	private boolean progressReportTab;
	private boolean responseFormTab;
	private boolean showDraft;

	private boolean displayConformation = false;
	private int staticTabCount;

	private int lastActiveTabIndex;
	private Map<String, Integer> staticTabIdToIndexMap;
	private boolean hasBeenInitialized;
	private int inActiveTabsCount;

	public HomeBean() {
		init();
	}

	public void init() {
		logger.debug("................... homeBean Initializing ...................");
		tabView.setDynamic(true);
		tabView.setCache(true);
		userName="Raghu";
		setDataUploadDownloadTab(true);

		String selectedTab = getSelectedTab();
		int defaultActiveTab = 0;
		if (selectedTab != null) {
			defaultActiveTab = Integer.parseInt(selectedTab);
		}

		dynamicTabList = new ArrayList<Tab>();
		staticTabIdToIndexMap = new HashMap<String, Integer>();
		lastActiveTabIndex = 0;
		hasBeenInitialized = false;
		inActiveTabsCount = 0;
		showDraft = false;
		staticTabCount = 0;

		this.initialize();

		switch (defaultActiveTab) {
		case 0: {
			setDataUploadDownloadTab(true);
			tabView.setActiveIndex(0);
			break;

		}
		case 1: {
			setDesignWrorkflowTab(true);
			tabView.setActiveIndex(1);
			break;
		}
		case 2: {
			setProgressReportTab(true);
			tabView.setActiveIndex(2);
			break;

		}
		case 3: {
			setResponseFormTab(true);
			tabView.setActiveIndex(3);
			break;
		}

		default:
			setDataUploadDownloadTab(true);
			break;
		}
	}

	private void initialize() {
		staticTabCount = 0;
		List<UIComponent> tabViewChildren = tabView.getChildren();
		logger.debug("tabViewChildren.size() = " + tabViewChildren.size());
		Iterator<UIComponent> iterator = tabViewChildren.iterator();
		while (iterator.hasNext()) {
			UIComponent uiComponent = iterator.next();
			if (uiComponent.getClass().getName().equals(Tab.class.getName())) {
				logger.debug("static tab id = " + ((Tab) uiComponent).getId());
				staticTabIdToIndexMap.put(((Tab) uiComponent).getId(),
						staticTabCount);
				staticTabCount++;
			}
		}
		if (staticTabCount > 0) {
			this.hasBeenInitialized = true;
			logger.debug("has been initialized with staticTabCount = "
					+ staticTabCount);
		}
	}

	private String getSelectedTab() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String doParam = fc.getExternalContext().getRequestParameterMap()
				.get("do");
		if (doParam != null) {
			String baIdString = fc.getExternalContext()
					.getRequestParameterMap().get("baId");
			if (baIdString != null) {

				String tabIdString = fc.getExternalContext()
						.getRequestParameterMap().get("tab");
				if (tabIdString != null) {
					return tabIdString;
				}
			}
		}
		return null;
	}

	// ///////////////////////////////////////////////////////
	/**
	 * 
	 * 
	 * @param event
	 *            Event captured while changing tab
	 */
	public void onTabChange(TabChangeEvent event) {

		Long startTime = System.currentTimeMillis();
		if (showDraft) {
			inActiveTabsCount = 0;
		} else {
			inActiveTabsCount = 1;
		}

		if (this.hasBeenInitialized == false) {
			this.initialize();
		}

		String activeTabId = event.getTab().getId();

		Integer activeTabIndex = staticTabIdToIndexMap.get(activeTabId);
		if (activeTabIndex == null) {
			activeTabIndex = staticTabCount - inActiveTabsCount;
			for (Tab dynamicTab : dynamicTabList) {
				if (dynamicTab.isRendered() == true) {
					if (dynamicTab.getId().equals(activeTabId)) {
						break;
					}
					activeTabIndex += 1;
				}
			}
		} else {
			if (activeTabId.equals("searchTabId")) {
				showSearchTab();

			} else if (activeTabId.equals("myRequestsTabId")) {
				showMyRequestTab();

			} else if (activeTabId.equals("reportsTabId")) {
				showMyReportsTab();

			} else if (activeTabId.equals("dashboardTabId")) {
				showDashBoardTab();
			}
		}

		tabView.setActiveIndex(activeTabIndex.intValue());
		Long endTime = System.currentTimeMillis();
		logger.info("Time taken to change tab: " + (endTime - startTime));
	}

	private void showSearchTab() {
		setDataUploadDownloadTab(true);
		setProgressReportTab(false);
		setDesignWrorkflowTab(false);
		setResponseFormTab(false);
	}

	private void showMyReportsTab() {
		setProgressReportTab(true);
		setDataUploadDownloadTab(false);
		setDesignWrorkflowTab(false);
		setResponseFormTab(false);
	}

	private void showMyRequestTab() {
		setDesignWrorkflowTab(true);
		setDataUploadDownloadTab(false);
		setProgressReportTab(false);
		setResponseFormTab(false);
	}

	private void showDashBoardTab() {
		setResponseFormTab(true);
		setDesignWrorkflowTab(false);
		setDataUploadDownloadTab(false);
		setProgressReportTab(false);
	}

	// ///////////////////////////////////
	/**
	 * on tabClose event, this removes the closed tab from component tree and
	 * user is navigated to tab before
	 * 
	 * @param event
	 *            Event captured while closing tab
	 */

	public void onTabClose(TabCloseEvent event) {
	}

	public void addNewDynamicTab(Tab tabToAdd, boolean setActive) {
		if (this.hasBeenInitialized == false) {
			this.initialize();
		}

		if (showDraft) {
			inActiveTabsCount = 0;
		} else {
			inActiveTabsCount = 1;
		}

		if (tabToAdd != null) {
			dynamicTabList.add(tabToAdd);
			if (setActive == true) {
				tabView.setActiveIndex(staticTabCount
						+ (dynamicTabList.size() - 1) - inActiveTabsCount);
				logger.debug("Id of Added Tab " + tabToAdd.getId());
				logger.debug("Added a new tab , set active tab index = "
						+ tabView.getActiveIndex()
						+ ", dynamicTabList.size() = " + dynamicTabList.size()
						+ ", inActiveTabsCount = " + inActiveTabsCount);
			}
		}
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isDataUploadDownloadTab() {
		return dataUploadDownloadTab;
	}

	public void setDataUploadDownloadTab(boolean dataUploadDownloadTab) {
		this.dataUploadDownloadTab = dataUploadDownloadTab;
	}

	public boolean isDesignWrorkflowTab() {
		return designWrorkflowTab;
	}

	public void setDesignWrorkflowTab(boolean designWrorkflowTab) {
		this.designWrorkflowTab = designWrorkflowTab;
	}

	public boolean isProgressReportTab() {
		return progressReportTab;
	}

	public void setProgressReportTab(boolean progressReportTab) {
		this.progressReportTab = progressReportTab;
	}

	public boolean isResponseFormTab() {
		return responseFormTab;
	}

	public void setResponseFormTab(boolean responseFormTab) {
		this.responseFormTab = responseFormTab;
	}

	public boolean isHasBeenInitialized() {
		return hasBeenInitialized;
	}

	public void setHasBeenInitialized(boolean hasBeenInitialized) {
		this.hasBeenInitialized = hasBeenInitialized;
	}

	public TabView getTabView() {
		return this.tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public int getStaticTabCount() {
		return staticTabCount;
	}

	public void setStaticTabCount(int staticTabCount) {
		this.staticTabCount = staticTabCount;
	}

	public List<Tab> getDynamicTabList() {
		return dynamicTabList;
	}

	public void setDynamicTabList(List<Tab> dynamicTabList) {
		this.dynamicTabList = dynamicTabList;
	}

	public int getLastActiveTabIndex() {
		return lastActiveTabIndex;
	}

	public void setLastActiveTabIndex(int lastActiveTabIndex) {
		this.lastActiveTabIndex = lastActiveTabIndex;
	}

	public Map<String, Integer> getStaticTabIdToIndexMap() {
		return staticTabIdToIndexMap;
	}

	public void setStaticTabIdToIndexMap(
			Map<String, Integer> staticTabIdToIndexMap) {
		this.staticTabIdToIndexMap = staticTabIdToIndexMap;
	}

	public int getInActiveTabsCount() {
		return inActiveTabsCount;
	}

	public void setInActiveTabsCount(int inActiveTabsCount) {
		this.inActiveTabsCount = inActiveTabsCount;
	}

	public boolean isShowDraft() {
		return showDraft;
	}

	public void setShowDraft(boolean showDraft) {
		this.showDraft = showDraft;
	}

	public boolean isDisplayConformation() {
		return displayConformation;
	}

	public void setDisplayConformation(boolean displayConformation) {
		this.displayConformation = displayConformation;
	}

	public void showDraftInView() {

		setShowDraft(true);
		if (hasBeenInitialized == false) {
			this.initialize();
		}
		tabView.setActiveIndex(staticTabCount - 1);
	}
}
