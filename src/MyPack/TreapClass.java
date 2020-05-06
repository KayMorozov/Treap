package MyPack;


import java.util.Random;

//Проект для курсовой по теории графов.
public class TreapClass<T> implements Tree<T> {

    static private Random rand = new Random();
    public int id;
    public int y;
    public TreapClass Left;
    public TreapClass Right;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreapClass(int id, T data){
        this(id, rand.nextInt(), data);
    }

    private TreapClass(int id, int y, T data){
        this.id = id;
        this.y = y;
        this.Left = null;
        this.Right = null;
        this.data = data;
    }

    private TreapClass(int id, int y, TreapClass left, TreapClass right, T data){
        this.id = id;
        this.y = y;
        this.Left = left;
        this.Right = right;
        this.data = data;
    }


    public static TreapClass Merge(TreapClass L, TreapClass R){
        if (L == null) return R;
        if(R == null) return L;

        if(L.y > R.y) {
            return new TreapClass(L.id, L.y, L.Left, Merge(L.Right, R),L.data);
        }else{
            return new TreapClass(R.id, R.y, Merge(L, R.Left), R.Right,R.data);
        }

    }

    public TreapClass[] Split(int id){
        TreapClass newTree = null;
        TreapClass L,R;
        if(this.id < id){
            if(Right == null)
                R = null;
            else{
                TreapClass[] gg = Right.Split(id);
                newTree = gg[0];
                R = gg[1];
            }
            L = new TreapClass (this.id, y, Left, newTree,this.data);
        }else{
            if(Left == null)
                L = null;
            else{
                TreapClass[] gg = Left.Split(id);
                L = gg[0];
                newTree = gg[1];
            }
            R = new TreapClass(this.id, y, newTree, Right,this.data);
        }
        return new TreapClass[]{L,R};
    }

    @Override
    public boolean push(int id, T data){
        TreapClass tree = Add(id,data);
        this.id = tree.id;
        this.y = tree.y;
        this.Left = tree.Left;
        this.Right = tree.Right;
        this.data = (T) tree.data;
        return true;
    }

    private TreapClass Add(int id, T data) {
        TreapClass[] t = Split(id);
        TreapClass l = t[0];
        TreapClass r = t[1];
        TreapClass m = new TreapClass(id, data);
        return  Merge(Merge(l,m),r);
    }

    @Override
    public T pop(int id) {
        return (T) Remove(id)[1];
    }

    private Object[] Remove(int id){
        T d = null;
        if(this.id == id){
            return new Object[]{ Merge(Left, Right),data};
        }
        if(this.id > id)
            if(Left != null){
                Object[] gg = Left.Remove(id);
                Left = (TreapClass)gg[0];
                d = (T)gg[1];
            }
        if(this.id < id)
            if(Right != null) {
                Object[] gg = Right.Remove(id);
                Right = (TreapClass)gg[0];
                d = (T)gg[1];
            }
        return new Object[]{this,d};
    }

    @Override
    public T find(int value){
        if(id == value)
            return data;
        if(id > value)
            if(Left != null)
                return (T) Left.find(value);
        if(id < value)
            if(Right != null)
                return (T) Right.find(value);
        return null;

    }

    public int deep(TreapClass L, TreapClass R) {
        int deep = 1;
        int right = 0;
        int left = 0;
        if(L == null & R == null)
            return deep;
        if(L != null)
            right += deep + deep(L.Left, L.Right);
        if(R != null)
            left += deep + deep(R.Left, R.Right);
        if(left > right)
            return left;
        else
            return right;
    }
}
