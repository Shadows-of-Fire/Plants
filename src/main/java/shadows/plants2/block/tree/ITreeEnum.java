package shadows.plants2.block.tree;

import shadows.placebo.interfaces.IPropertyEnum;

public interface ITreeEnum<E extends Enum<E> & ITreeEnum<E>> extends IPropertyEnum {

	public Tree<E> getTree();

	public void setTree(Tree<E> tree);

}