/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeBasicView")
@ViewScoped
public class BasicView implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private TreeNode root;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("Start", null);
		TreeNode dnaSeq = new DefaultTreeNode("DNA Sequencing", root);
		TreeNode rnaSeq = new DefaultTreeNode("RNA-Sequencing", root);
		TreeNode chipSeq = new DefaultTreeNode("Chip-Sequencing", root);

/*		TreeNode preProcessingDNASeq = new DefaultTreeNode("Pre Processing", dnaSeq);
		TreeNode processingDNASeq = new DefaultTreeNode("Processing", dnaSeq);
		TreeNode postProcessingDNASeq = new DefaultTreeNode("Post Processing", dnaSeq);

		TreeNode preProcessingRNASeq= new DefaultTreeNode("Pre Processing", rnaSeq);
		TreeNode processingRNASeq = new DefaultTreeNode("Processing", rnaSeq);
		TreeNode postProcessingRNASeq = new DefaultTreeNode("Post Processing", rnaSeq);

		TreeNode preProcessingChipSeq = new DefaultTreeNode("Pre Processing", chipSeq);
		TreeNode processingChipSeq = new DefaultTreeNode("Processing", chipSeq);
		TreeNode postProcessingChipSeq = new DefaultTreeNode("Post Processing", chipSeq);
*/
		dnaSeq.getChildren().add(new DefaultTreeNode("Pre Processing"));
		dnaSeq.getChildren().add(new DefaultTreeNode("Processing"));
		dnaSeq.getChildren().add(new DefaultTreeNode("Post Processing"));
		
		rnaSeq.getChildren().add(new DefaultTreeNode("Pre Processing"));
		rnaSeq.getChildren().add(new DefaultTreeNode("Processing"));
		rnaSeq.getChildren().add(new DefaultTreeNode("Post Processing"));
		
		
		chipSeq.getChildren().add(new DefaultTreeNode("Pre Processing"));
		chipSeq.getChildren().add(new DefaultTreeNode("Processing"));
		chipSeq.getChildren().add(new DefaultTreeNode("Post Processing"));
		
		
		
	
		
	}

	public TreeNode getRoot() {
		return root;
	}
}
