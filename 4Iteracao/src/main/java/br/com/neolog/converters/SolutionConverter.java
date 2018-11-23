package br.com.neolog.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Equivalence;
import com.google.common.base.Equivalence.Wrapper;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.Product;
import br.com.neolog.models.SeparatedItem;
import br.com.neolog.models.Solution;
import br.com.neolog.repository.ProductRepository;

/**
 * Classe responsável por criar um resultado que será devolvido com base no
 * Solution recebido como parâmetro.
 *
 * @author igor.kurman
 */
@Component
public class SolutionConverter
{
    @Autowired
    private ProductRepository productRepository;

    /**
     * @param solution contém o Set de HolderCodePrice usado para gerar o Map
     *        que será retornado para o usuário.
     * @return o Map com todos os produtos e quantidades fornecidos pela
     *         Solution.
     */
    public PresentationSolution convert(
        final Solution solution )
    {

        if( solution.getProducts() == null ) {
            return PresentationSolution.of( Collections.emptyList(), 0 );
        }

        final Multiset<Wrapper<HolderCodeValue>> productCodes = HashMultiset.create();
        for( final HolderCodeValue holder : solution.getProducts() ) {
            productCodes.add( holderCodeEquivalence.wrap( holder ) );
        }

        long total = 0;
        final List<SeparatedItem> items = new ArrayList<>();
        for( final Entry<Wrapper<HolderCodeValue>> entry : productCodes.entrySet() ) {
            final Wrapper<HolderCodeValue> wrapper = entry.getElement();
            final HolderCodeValue holder = wrapper.get();
            final int quantity = entry.getCount();
            final Product product = productRepository.findByCode( holder.getCode() );
            total += holder.getValue() * quantity;
            items.add( SeparatedItem.newInstance( product.getCode(), product.getName(), quantity, holder.getValue() ) );
        }

        return PresentationSolution.of( items, total );

    }

    private static final Equivalence<HolderCodeValue> holderCodeEquivalence = new Equivalence<HolderCodeValue>() {

        @Override
        protected boolean doEquivalent(
            final HolderCodeValue a,
            final HolderCodeValue b )
        {
            return Objects.equals( a.getCode(), b.getCode() );
        }

        @Override
        protected int doHash(
            final HolderCodeValue t )
        {
            return Objects.hash( t.getCode() );
        }
    };
}
