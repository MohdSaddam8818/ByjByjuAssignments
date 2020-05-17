package com.byjuassignmsaddam.activity

import android.os.Bundle
import android.text.Html
import android.view.View

import androidx.databinding.DataBindingUtil
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmsaddam.R
import com.byjuassignmsaddam.constant.AppConstant
import com.byjuassignmsaddam.databinding.ActivityNewsDetailsBinding
import com.byjuassignmsaddam.utility.CommonUtils
import com.byjuassignmsaddam.viewmodel.NewsDetailsViewModel
import com.ludis.fitness.app.mvvm.baseMVVM.BaseMvvmActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject

class NewsDetailsActivity : BaseMvvmActivity<NewsDetailsViewModel>(), View.OnClickListener {


    @Inject
    lateinit var newsDetailsViewModel: NewsDetailsViewModel

    override fun getVM(): NewsDetailsViewModel = newsDetailsViewModel
    lateinit var binding: ActivityNewsDetailsBinding
    lateinit var newsDatails: NewsDetailModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        setListener()
        getBundleData()
    }


    fun getBundleData() {

        val Intent = getIntent().getExtras()
        if (Intent != null) {
            Intent.classLoader = javaClass.classLoader
            if (getIntent().hasExtra(AppConstant.NewsDetails)) {
                newsDatails = Intent.getParcelable(AppConstant.NewsDetails)!!
                ViewDetails(newsDatails)
            }
        } else {

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.ivToolBarBack -> {
                finish()
            }


        }
    }


    /**
     * @return manage request onClick listener on views
     */
    //manage send result
    private fun setListener() {
        binding.ivToolBarBack.setOnClickListener(this)

    }


    fun ViewDetails(newsDetailsModel: NewsDetailModel) {

        if (newsDetailsModel != null) {


            binding.tvTitle.text = newsDetailsModel.title
            binding.tvNewsName.text = newsDetailsModel.name
            binding.tvDescription.text = Html.fromHtml(newsDetailsModel.description)
            binding.tvDate.text = CommonUtils().getDateFormet(newsDetailsModel.publishedAt!!)

            if (newsDetailsModel.urlToImage != null && !newsDetailsModel.urlToImage.toString().isEmpty()) {
                Picasso.with(this).load(newsDetailsModel.urlToImage)
                    .placeholder(R.drawable.ic_avatar).fit().into(binding!!.clBackground, object :
                    Callback {
                    override fun onSuccess() {

                    }

                    override fun onError() {

                    }
                })
            } else {
                binding!!.clBackground.setBackgroundColor(R.drawable.ic_avatar)
            }

        }

    }
}